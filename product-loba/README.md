
# Product Load Balancer & Cricuit Breaker
This application acts as Load Balancer and Cricuit breaker for [Product Microservice](https://github.com/meta-magic/microservice_workshop/tree/master/product-service)




# Load Balancing
Load Balancing automatically distributes incoming application traffic between number of nodes running for given application.

Ribbon :

This provide client side load balancing. Its component offers a good set of configuration options such as connection timeouts, retries, retry algorithm  etc.

Strategies offered by ribbon are listed below:

- Simple Round Robin LB
- Weighted Response Time LB
- Zone Aware Round Robin LB
- Random LB

# Circuit Breaker pattern

Netflix’s Hystrix library provides an implementation of the Circuit Breaker pattern: when we apply a circuit breaker to a method, Hystrix watches for failing calls to that method, and if failures build up to a threshold, Hystrix opens the circuit so that subsequent calls automatically fail. While the circuit is open, Hystrix redirects calls to the method, and they’re passed on to our specified fallback method. 
