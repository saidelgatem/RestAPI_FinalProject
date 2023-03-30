# TestNG_Maven-demo.midtrans.com

## Technology used:
- Tool: Selenium
- Language: Java
- IDE: Intellij IDEA
- Build tool: Maven
- Framework: TestNG , Rest-Assured
- 

## Prerequisite:
1. Need to install jdk 11 and configure it into Environment variable
2. Download maven and configure it into Environment variable also

## Run the automation script:
1. Open the project with Intellij IDEA by clicking on pom.xml
2. Let the Intellij IDEA to download the dependencies for the first run
3. After dependencies are downloaded and project loaded successfully, click on Terminal from the bottom bar
4. To run test, Right click on "testng.xml" file and click on "Run" or Type in the terminal:
```sh
mvn clean test
```
6. Tests will open on RUN tab.
7. To view report of the automation execution, expand the target->surefire-reports folder and open "index.html" in a browser
```sh
```
## Test run report
![image](https://github.com/saidelgatem/RestAPI_FinalProject/blob/master/target/ResAPI_report.png)
