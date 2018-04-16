# Order Microservice

The order service is responsible for providing an API to facilitate the ordering of products that a user has purchased. Application is build using

- Spring
- Kafka
- Mongodb

# Configuration
Order service is configured on on port 8583, for more information regarding configuration please check [orderservice.yml](https://github.com/meta-magic/microservice_workshop/blob/master/config-files/orderservice.yml). Make below changes in [orderservice.yml](https://github.com/meta-magic/microservice_workshop/blob/master/config-files/orderservice.yml) for application to run.
- Specify mongo db host, port , host & userid/password (if configured).
- Specify kafka host and port. For kafka installaion please refere [here](https://ketangote.wordpress.com/2017/03/02/install-zookeeper-kafka-steps/).

# Run
Execute "mvn spring-boot:run" command from order-service folder.
