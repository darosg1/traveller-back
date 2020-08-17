# traveller-back

The application has been written in Java under the Spring framework. Application was developed in the REST architecture.

build
To build the project please unzip file to an internal folder. The unzipped folder contains source and the gradle.build file. 
The project can be executed with gradle.build at any environment which has preloaded Gradle engine. 

concept of the project
Application author was inspired by self-service holidays planners services provided by huge travel agency. 
The application was designed to be able to attached ta any holidays booking system. 
Customer functionality allows to select excursion, flights, hotels and check weather forecast for requested area.

project structure
Project structure contains several separate layers: 
	Domain: where all domain object are stored;
	Entity: where all objects are stored;
	Mappers: which steers of incomming and outcomming object data transfer;
	Controllers: rest controllers with the api endpoints;
	Services: where requested functionality and business logic is implemented. 
Etc.

unit test
Unit Test with coverage >60% was written and succesfully executed over the entire code.

api documentation
The Swagger library was implemented in CoreConfiguration.class to get access to the documentation api for all controllers. 
After application execution (locally) the API documentation will be available under the following address: http://localhost:8080/swagger-ui.html.
It presents possible endpoints for all controller used in the project with examples of response(JSON).
