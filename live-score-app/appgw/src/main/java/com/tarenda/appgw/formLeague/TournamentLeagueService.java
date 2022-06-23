package com.tarenda.appgw.formLeague;

import com.tarenda.clients.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public record TournamentLeagueService(TournamentClient tournamentClient, LeagueClient leagueClient) {

    @Value("${filePath}")
    private static String address;

    public void  formLeague() throws IOException {
        LeagueResponseDTO leagueResponse;
        if(address!=null) {
            TournamentRequestDTO tournamentRequest = extractTournament();
            leagueResponse = tournamentClient.processTournament(tournamentRequest);
        }else{
            leagueResponse = leagueClient.retrieveLeague();
            System.out.println("No file location for a New Tournament was added");
            System.out.println("However Our current League is like this:");
        }
        if(leagueResponse!=null && leagueResponse.getTeams()!=null) {
            int counter =1;
            for (Map.Entry<String, Integer> entry: leagueResponse.getTeams().entrySet()) {
                System.out.println(counter + ". "+ entry.getKey() + ", " + entry.getValue() + " pts");
                counter+=1;
            }
        }
    }

    private TournamentRequestDTO extractTournament() throws IOException {
//        String address = "c:\\temp\\demo.txt";
        System.out.println(address);
        TournamentRequestDTO tournamentRequest = new TournamentRequestDTO();
        List<ScorelineDTO> scorelineDTOList = new ArrayList<>();

        try (Stream<String> stream
                     = Files.lines(Paths.get(address), StandardCharsets.UTF_8)) {
            stream.forEach(s -> scorelineDTOList.add(buildScoreline(s)));
        } catch (IOException e) {
            throw new IOException("File not found: " + address);
        }
        if(scorelineDTOList.isEmpty()) throw new IllegalArgumentException("No scoreline was found in the file: " +address);
        tournamentRequest.setScorelines(scorelineDTOList);
        return tournamentRequest;
    }

    private ScorelineDTO buildScoreline(String scoreLine) {
        ScorelineDTO scorelineDTO = new ScorelineDTO();
        try {
            String[] teamScore = scoreLine.trim().split(",");
            scorelineDTO.setTeamAName(extractName(teamScore[0]));
            scorelineDTO.setTeamAScore(extractDigit(teamScore[0]));
            scorelineDTO.setTeamBName(extractName(teamScore[1]));
            scorelineDTO.setTeamBScore(extractDigit(teamScore[1]));
        }catch(Exception e){
             throw new IllegalArgumentException("An invalid scoreline was found: " + scoreLine, e);
        }
        return scorelineDTO;
    }

    private int extractDigit(String teamScore){
        StringBuilder number= new StringBuilder();
        for(int i = teamScore.length()-1 ; i >= 0; i-- ){
            if(Character.isDigit(teamScore.charAt(i))){
                number.insert(0, teamScore.charAt(i));
            }
            if(Character.isSpaceChar(teamScore.charAt(i))){
               break;
            }
        }
        if(StringUtils.isBlank(number.toString())) throw new IllegalArgumentException("A blank score was found for team: " + teamScore);
        return  Integer.parseInt(number.toString());
    }

    private String extractName(String teamScore){
        StringBuilder name= new StringBuilder();
        for(int i = teamScore.length()-1 ; i >= 0; i-- ){
            if(Character.isAlphabetic(teamScore.charAt(i)) || Character.isSpaceChar(teamScore.charAt(i))){
                name.insert(0, teamScore.charAt(i));
            }
        }
        if(StringUtils.isBlank(name.toString())) throw new IllegalArgumentException("A blank team name was found with score: " + teamScore);
        return name.toString().trim();
    }
}
