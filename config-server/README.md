# Config Server

For single standalone application we can keep all required configuration bundle with application itself.  However, when we have more than one application, say in a microservice architecture, a better alternative would be to manage the configurations centrally.

With the Config Server we have a central place to manage external properties for applications with support for different environments. Configuration files in several formats like YAML or properties are added to a Git repository.

# Features

## Spring Cloud Config Server features:

HTTP, resource-based API for external configuration (name-value pairs, or equivalent YAML content)
Encrypt and decrypt property values (symmetric or asymmetric)
Embeddable easily in a Spring Boot application using @EnableConfigServer

## Config Client features (for Spring applications):

Bind to the Config Server and initialize Spring Environment with remote property sources
Encrypt and decrypt property values (symmetric or asymmetric)


# Installation changes  
Current config server is configured with local file system, its configuration are defined in [application.properties](https://github.com/meta-magic/microservice_workshop/blob/master/config-server/src/main/resources/application.properties). Change the path to folder where you have put all [config files](https://github.com/meta-magic/microservice_workshop/tree/master/config-files).

# Run
Execute "mvn spring-boot:run" command from config-server folder , This will start config server at port 6061 which is configured in [application.properties](https://github.com/meta-magic/microservice_workshop/blob/master/config-server/src/main/resources/application.properties). 
