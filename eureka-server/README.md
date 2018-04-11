
# Eureka Server (Service Discovery)

The Eureka server is nothing but a service discovery pattern implementation, where every microservice is registered and a client microservice looks up the Eureka server to get a dependent microservice to get the job done.

The Eureka Server is a Netflix OSS product, and Spring Cloud offers a declarative way to register and invoke services by Java annotation.


# Eureka Server Communication
Every microservice registers itself in the Eureka server when bootstrapped, generally using the {ServiceId} it registers into the Eureka server, or it can use the hostname or any public IP (if those are fixed). After registering, every 30 seconds, it pings the Eureka server to notify it that the service itself is available. If the Eureka server not getting any pings from a service for a quite long time, this service is unregistered from the Eureka server automatically and the Eureka server notifies the new state of the registry to all other services. 

# Application
Eureka server is configured on on port 7070, for more information regarding configuration please check [eurekaserver.yml](https://github.com/meta-magic/microservice_workshop/blob/master/config-files/eurekaserver.yml).
