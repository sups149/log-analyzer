# log-analyzer
This application will help to scan json formatted log files and calculates time taken by each event.
Whenever an event takes more than the permissible limit, an alert information is stored in DB for that particular event.   

## Getting Started
This instruction will help to build, test and configure the application. 

### Prerequisites
1. JDK8 should be installed, JAVA_HOME and $JAVA_HOME/bin should be configured in environment variable and PATH respectively.
2. Gradle should be installed, GRADLE_HOME and GRADLE_HOME/bin should be configured in environment variable and PATH respectively.

### Installation
It can be cloned or downloaded from Github and can be imported into IDE of you choice as Gradle project.

### Build
To build this application run below command from project root directory:
gradle clean build

### Running the tests
To run the unit test cases run below command from project root directory:
gradle test