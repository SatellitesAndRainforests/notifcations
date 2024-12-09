## NotificationSender

A cli spring boot app that sends a http post to 
https://webhook.site 
for a notificationDetails.json file input.

## Requirements
- Java 17+
- Maven
- exsisting https://webhook.site webhook 

## set up 
1. git clone https://github.com/SatellitesAndRainforests/notifcations.git
    or
   download ZIP and unzip.
2. Navigate to NotificationSender/

## compile and run in NotificationSender/
```bash
mvn install
mvn test
mvn clean package


java -jar target/NotificationSender-1.0-SNAPSHOT.jar ./notificationDetails.json


