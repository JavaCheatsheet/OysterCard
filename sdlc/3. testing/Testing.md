# Testing

## Run Unit Test From CLI
Maven is used as build tool which also helps to
run unit tests easily.

- Run all tests: `mvn clean test`
- Run one test class: `mvn test -Dtest=TubeFareTest`
- Run two test classes: `mvn test -Dtest=TubeFareTest,JourneyTest`
- Run a specific test function: `mvn test -Dtest=TubeFareTest#givenMinimumBalance_TravelFromEarlsCourt_ToHammersmith`
