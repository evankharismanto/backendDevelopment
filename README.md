# backendDevelopment   
IES Malaysia Dev Training Camp    

Mission8
-For check health:
at postman, run get on this url: "http://localhost:8080/actuator/health"

-To test @RefreshScope:
1. At postman, run get on this url:"http://localhost:8080/hello"
2. change "https://github.com/evankharismanto/centralizedconfig.git"
file "helloworldspring-prod.properties" . "hello.message" value into something else
3. At postman, run get on this url:"http://localhost:8080/hello" -> value will not be changed yet
4. At postman, run post on this url:"http://localhost:8080/actuator/refresh"
5. At postman, run get on this url:"http://localhost:8080/hello" -> new value will be reflected

-To change value use /env:
1. At postman, run get on this url:"http://localhost:8080/hello" 
2. At postman, run get on this url:"http://localhost:8080/actuator/env/hello.message"
3. At postman, run post on this url:"http://localhost:8080/env" with requestbody JSON 
{"name":"hello.message","value":"Other Value Hello"}
4. At postman, run get on this url:"http://localhost:8080/hello" -> value will not reflected yet
5. At postman, run post on this url:"http://localhost:8080/actuator/refresh"
6. At postman, run get on this url:"http://localhost:8080/hello" -> new value will be reflected "Other Value Hello"

-to check custom @endpoint
1.  At postman, run get on this url:"http://localhost:8080/actuator" -> there are two additional endpoint 
"helloendpoint" and "customlov"
2.  At postman, run get on this url:"http://localhost:8080/actuator/helloendpoint"  -> to test custom endpoint
3.  At postman, run get on this url:"http://localhost:8080/actuator/customlov"  -> to get custom lov set values
4.  At postman, run post on this url:"http://localhost:8080/actuator/customlov/NewLOVItem" with request body 
{"value":"New Lov value"} -> to insert new custom lov item
5.  At postman, run get on this url:"http://localhost:8080/actuator/customlov/NewLOVItem" -> to get only the specific customLOV
6.  At postman, run delete on this url:"http://localhost:8080/actuator/customlov/NewLOVItem" -> to removenewly inserted customLOV



Mission6 item <brought over>
Non-Mandatory header key "profile-name" for ad-hoc profile change (will not disturb profile set at the bootstrap.properties)   

list of values :      
"" for default     
"test" for test     
"prod" for prod     

