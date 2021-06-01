# LM-Pub-Sub

This project implements the Publisher and Subscriber model. There can be multiple users subscribed to mulitple topics. Each message sent to a topic will be published to all the subscribed Users.

Steps to Run:
- Download and Extract the project
- Import in intelliJ/Eclipse using pom.xml
- This project uses Springboot Application Context, and should download all the required dependencies.
- Run the main class "Pub4Application"


Available Commands:
- addUser userName role
- addTopic topicName userName(admin access only)
- subscribeTopic topicName userName
- publishMessage messageBody
  Sample messageBody: {"id":"12","topicName":"topic","text":"Hi"}   [Please do not use any space/enter]
- processMessages
- viewSubscribedTopics userName
- removeUser userName1 userName2(admin access only)
- removeTopic topicName userName(admin access only)
