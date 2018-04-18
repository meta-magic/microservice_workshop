# Auth Microservice

The auth service is responsible for providing an API to authenticate user who wishes to order items from shopping app. User need to register himself with the shopping app in order to purchase items. Application is build using

- Spring
- Kafka
- Postgresql

# Configuration
Auth service is configured on on port 8585, for more information regarding configuration please check [authservice.yml](https://github.com/meta-magic/microservice_workshop/blob/master/config-files/authservice.yml). Make below changes in [authservice.yml](https://github.com/meta-magic/microservice_workshop/blob/master/config-files/authservice.yml) for application to run.
- Specify postgres db host, port , host & userid/password (if configured).
- Specify kafka host and port. For kafka installaion please refere [here](https://ketangote.wordpress.com/2017/03/02/install-zookeeper-kafka-steps/).

# Run
Execute "mvn spring-boot:run" command from auth-service folder.
