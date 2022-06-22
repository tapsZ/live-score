package com.tarenda.appgw.formLeague;

import com.tarenda.clients.LeagueResponseDTO;
import com.tarenda.clients.ScorelineDTO;
import com.tarenda.clients.TournamentClient;
import com.tarenda.clients.TournamentRequestDTO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public record TournamentLeagueService(TournamentClient tournamentClient) {

//    @Value("${filePath}")
//    private static String address;

    public void  formLeague() {
        TournamentRequestDTO tournamentRequest = extractTournament();
        LeagueResponseDTO leagueResponse = tournamentClient.recordScoreline(tournamentRequest);
        System.out.println(leagueResponse);
    }

    private TournamentRequestDTO extractTournament() {
        String address = "c:\\temp\\demo.txt";
        System.out.println(address);
        TournamentRequestDTO tournamentRequest = new TournamentRequestDTO();
        List<ScorelineDTO> scorelineDTOList = new ArrayList<>();
        Path filePath = Path.of(address);

        try (Stream<String> stream
                     = Files.lines(Paths.get(address), StandardCharsets.UTF_8)) {
            stream.forEach(s -> scorelineDTOList.add(buildScoreline(s)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tournamentRequest.setScorelines(scorelineDTOList);
        return tournamentRequest;
    }

    private ScorelineDTO buildScoreline(String scoreLine) {
        ScorelineDTO scorelineDTO = new ScorelineDTO();
        String[] teamScore =  scoreLine.trim().split(",");
        scorelineDTO.setTeamAName(extractName(teamScore[0]));
        scorelineDTO.setTeamAScore(extractDigit(teamScore[0]));
        scorelineDTO.setTeamBName(extractName(teamScore[1]));
        scorelineDTO.setTeamBScore(extractDigit(teamScore[1]));
        return scorelineDTO;
    }

    private int extractDigit(String teamScore){
        StringBuilder number= new StringBuilder();
        for(int i = teamScore.length()-1 ; i > 0; i-- ){
            if(Character.isDigit(teamScore.charAt(i))){
                number.insert(0, teamScore.charAt(i));
            }
            if(Character.isSpaceChar(teamScore.charAt(i))){
               break;
            }
        }
        return StringUtils.isNotBlank(number.toString()) ? Integer.parseInt(number.toString()): 0;
    }

    private String extractName(String teamScore){
        StringBuilder name= new StringBuilder();
        for(int i = teamScore.length()-1 ; i > 0; i-- ){
            if(Character.isAlphabetic(teamScore.charAt(i)) || Character.isSpaceChar(teamScore.charAt(i))){
                name.insert(0, teamScore.charAt(i));
            }
        }
        return name.toString().trim();
    }
}
