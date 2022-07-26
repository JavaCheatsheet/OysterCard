# Development

Our user-facing application is command-line(CLI).
To run our main app, we can do it in two ways.

### Via Docker
- Build Image: `sudo docker build . -t oystercard`
- Run CLI: `sudo docker run -it --rm oystercard:latest`

### Run Project
- Go to the project folder
- Build: `mvn clean package`
- Run CLI: `mvn exec:java`
- Another way: `java -jar target/OysterCard-1.0-SNAPSHOT.jar`
