# gemini-api-client-avec-springboot

Environnement JDK 21 installez :

pour construire l'éxecutable .jar, situez vous à la racine du projet (ou il y a le pom.xml)

    mvn clean package

lancer l'application avec : 

    java -jar gemini_chatbot-0.0.1-SNAPSHOT.jar


Format de requete JSON sur Postman :

  url :

    http://localhost:8080/api/chat


  Méthode :
  
    POST

    
  JSON body :

    {"message": "entrez votre message"}
