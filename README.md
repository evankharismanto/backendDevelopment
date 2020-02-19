# backendDevelopment
IES Malaysia Dev Training Camp

Test Postman : 



//Create

POST : localhost:8080/create

Request Body :
{"orders":[{ "id" : 1, "date" : "2020-02-14", "delivery" : 1, "address" : { "line1" : "address1 test string" }, "customer" : { "name" : "Terrice", "phoneNumber" : "01825516221", "address" : { "line1" : "address1 test string" }}, "item" : [{ "amount" : 1, "paint" : { "color" : "Green", "type" : "EasyToClean", "litre" : 5 }}]}]}



//Read

Get : localhost:8080/read



//Add

Post : localhost:8080/add

Request Body :
{ "id" : 2, "date" : "2020-02-14", "delivery" : 1, "address" : { "line1" : "address2 test string" }, "customer" : { "name" : "Verince", "phoneNumber" : "01825515110", "address" : { "line1" : "address2 test string" }}, "item" : [{ "amount" : 1, "paint" : { "color" : "Red", "type" : "EasyToClean", "litre" : 2 }}]}



//Update

Put : localhost:8080/update/2

Request Body :
{ "id" : 2, "date" : "2020-02-14", "delivery" : 0, "customer" : { "name" : "Verince", "phoneNumber" : "01825515110", "address" : { "line1" : "address2 test string" }}, "item" : [{ "amount" : 1, "paint" : { "color" : "Red", "type" : "EasyToClean", "litre" : 2 }}]}



//Delete

Delete : localhost:8080/delete/1
