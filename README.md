# live-score
An app that generates a league table from a list of score lines 

#Tech Stack
- Postgres
- MongoDB
- SpringBoot (Microservices)
- Docker
- Maven (Project Modules and Build Profiles)
- Jib (Building docker images and pushing to docker hub)
- Lombok
- Eureka Netflix (Service Discovery)
- Sleuth and Zipkin (distributed tracing)
- Spring Cloud Gateway
- JUnit5, AssertJ and Morkito

#How To Start Up
1. Git Clone 
2. cd into live-score\live-score-app //this will start the whole enviroment except for the appgw application
3. run docker compose up -d
4. cd into live-score\live-score-app\appgw
5. run mvn spring-boot:run -Dspring-boot.run.arguments="--spring.main.banner-mode=off --file.path=c:\temp\demo.txt"

Please Note that on number 5 replace "c:\temp\demo.txt" with the location of your input file

#ToDo
1. Fix the Command Line Spring Context issue
2. Finish up testing improve coverage and make sure all loopholes are closed
3. Add security especially on the microservices in the private network ref: live-score\live-score-app\live-score-architecture.drawio.svg
4. Improve on documentation maybe add swagger
5. Add CI/CD pipeline
6. Implement Kubernetes for Docker Containerization
7. Add kafka for better resiliance and fault tolarance
