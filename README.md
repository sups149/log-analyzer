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

Please change below Before build:
- Inside **resources/config.properties** change the configurations as appropriate:

    Example: **jdbc.url=jdbc:hsqldb:hsql://localhost/testdb**
    
- Run the DDL script **resources/schema.sql** in HSQLDB.

### Build
To build this application run below command from project root directory:

gradle clean build

### Running the tests
To run the unit test cases run below command from project root directory:

gradle test

### Deployment
1. Once the application has been built successfully, below directy structure will be created:
{PROJECT_ROOT_DIRECTOY}/builds/distributions
2. Inside above mentioned directory the deployable application will be present in archived format.
3. After the archive has been extracted to desired location, two scripts will be found inside below directory:
{EXTRACTED_ARCHIVE}/bin

    (a) logmonitor (Unix shell script)
    
    (b) logmonitor.bat (Windows batch)
4. Modify "DEFAULT_JVM_OPTS" and provide any of the below java options:
- -Dfile.path=**{The input log file path to scan}** -Dapp.name=log-analyzer -Dlog.path=**{application log path}** -Dspring.profiles.active=**{spring profiles}**

    Example: **-Dfile.path=/Users/sups/Repository_Git/log.txt -Dapp.name=log-analyzer -Dlog.path=/Users/log -Dspring.profiles.active=stream**
    
    **Note:** Here we are using two spring profiles: "stream" and "taskexecutor" see **Application Details** section for more information.
    

 

### Running the application
1. To run the application execute logmonitor or logmonitor.bat from respective environments.

## Application Details
The application has been developed with Spring and Hibernate ORM framework, for unit testing I have used Mockito mocking framework
and logback for logging.

Java 8 Features used:
- Lambdas
- Method references
- Interace with default method
- Stream API

I have developed two ways to handle file procession using multithreading approach.
- Java parallel stream using Collector and Groupingby
- Using ThreadPoolTask executor and then executing the runnables with multiple thread.

To separate the approaches I have used spring profiles.
So, while running the application please use either **-Dspring.profiles.active=stream** or **-Dspring.profiles.active=taskexecutor**  
