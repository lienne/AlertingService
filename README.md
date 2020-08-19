# Real Time Alerting Service

Real Time Alerting Service is a simple rest endpoint that receives a POST request containing server utilization data and returns an alert if server utilization is violating certain conditions, or returns no alert otherwise.
The service uses Spring Boot, Swagger, and JUnit for unit testing.


## Execution Instructions

1. Extract the zip file and load it in IntelliJ or your IDE of choice.
    - JDK 11 or higher needed.
    - Make sure you have Maven installed and in your path variable.
2. Open IntelliJ command line and run:
    `mvn clean install.`
    `java -jar target/service-0.0.1-SNAPSHOT.jar`
3. On your browser, go to [http://localhost:8080/swagger-ui.html/]
4. If the above address doesn't work, try [http://localhost:8080/swagger-ui.html#/]
5. In the Swagger UI, click on warning-controller.
6. Click on /checkServerUtilization.
7. Click on the "Try it out" button on the right-hand-side.
8. Erase the current string and input a test case.
		Sample Test Cases:

				1,2,3,4
				Expected output:
				(No Alert, 1)

				2234,90,50,40
				Expected output:
				(Alert, 1234, CPU_UTILIZATION VIOLATED)

				1234,89,69,65
				Expected output:
				(Alert, 1234, CPU_UTILIZATION VIOLATED, DISK_UTILIZATION VIOLATED)


9. Click on the blue "Execute" button
10. Server response should be 200 OK, and output will be on the Response Body box


## Unit Testing Instructions

1. On your IDE, open the file:

	`service > src > test >java > com.alerting.service > WarningControllerTest.java`

2.  Run the test file to run all tests


## Troubleshooting

#### Error: Port is in use
`Web server failed to start. Port 8080 was already in use.`

1. Go to service > src > main > resources > application.properties
2. Add a line to change your port number, like so:
		`server.port=9090`