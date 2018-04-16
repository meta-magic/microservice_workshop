# Shopping Cart Service
The shopping cart service is responsible for providing an API that manages the products that a user has chosen to add to their online shopping cart. Application focus on event sourcing and CQRS concept also. It is build using

- Spring
- AXON
- Mongodb
- Kafka

# Event Sourcing
Event sourcing is a software pattern where the state of data in a domain is captured and stored as a sequence of append-only events. Instead of storing the state of a data entity in a column or a property value, the state will be aggregated from a sequence of events. This service uses event sourcing to aggregate the state of a user's shopping cart.


# Configuration
Shopping Cart service is configured on on port 8582, for more information regarding configuration please check [shoppingcartservice.yml](https://github.com/meta-magic/microservice_workshop/blob/master/config-files/orderservice.yml). Make below changes in [shoppingcartservice.yml](https://github.com/meta-magic/microservice_workshop/blob/master/config-files/orderservice.yml) for application to run.
- Specify mongo db host, port , host & userid/password (if configured).
- Specify kafka host and port. For kafka installaion please refere [here](https://ketangote.wordpress.com/2017/03/02/install-zookeeper-kafka-steps/).

# Run
Execute "mvn spring-boot:run" command from order-service folder.
