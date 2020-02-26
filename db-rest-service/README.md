# backendDevelopment
IES Malaysia Dev Training Camp

To test :


Create / Add        
URL : localhost:8080/order      
Request Body :       
{"id":1,"date":"2020-02-17T00:00:00.000+0000","delivery":1,"customer":{"id":1,"name":"Terrice","phoneNumber":"01825516221","address":{"id":1,"line1":"address1Cust1 test string","line2":null,"line3":null}},"items":[{"id":1,"amount":1,"paint":{"id":1,"color":"Green","type":"EasyToClean","litre":5}},{"id":2,"amount":2,"paint":{"id":2,"color":"Blue","type":"SemiGloss","litre":5}}]}


Read List      
URL : localhost:8080/view      


Update      
URL : localhost:8080/replace/{id}
[{"id":1,"date":"2020-02-19T00:00:00.000+0000","delivery":0,"customer":{"id":1,"name":"Loca","phoneNumber":"01825511111","address":{"id":1,"line1":"address eddited test string","line2":"address1Cust1 test string","line3":null}},"items":[{"id":1,"amount":2,"paint":{"id":1,"color":"Red","type":"Weather","litre":5}},{"id":2,"amount":10,"paint":{"id":2,"color":"Orange","type":"Acrylic Paints","litre":2}}]}]


Delete     
URL : localhost:8080/remove/{id}
