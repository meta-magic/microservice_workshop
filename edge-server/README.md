
# Zuul to Proxy your Microservices

A common challenge when building microservices is providing a unified interface to the consumers of your system. The fact that your services are split into small composable apps shouldn’t be visible to users or result in substantial development effort.

To solve this problem, Netflix created and open-sourced its Zuul proxy server. Zuul is an edge service that proxies requests to multiple backing services. It is “front door” to your system, which allows a browser, mobile app, or other user interface to consume services from multiple hosts without managing cross-origin resource sharing (CORS) and authentication for each one.


# Spring Cloud
Spring Cloud has created an embedded Zuul proxy to ease the development of a very common use case where a UI application wants to proxy calls to one or more back end services. This feature is useful for a user interface to proxy to the backend services it requires, avoiding the need to manage CORS and authentication concerns independently for all the backends.

To enable it, annotate a Spring Boot main class with @EnableZuulProxy, and this forwards local calls to the appropriate service based on configuration defined in config file [edgeserver.yml](https://github.com/meta-magic/microservice_workshop/blob/master/config-files/edgeserver.yml)


Zuul has different types of filters that enables us to quickly apply functionality to our edge service. These filters help us perform the following functions:

- Authentication and Security.
- Insights and Monitoring.
- Dynamic Routing.
- Stress Testing.
- Load Shedding.
- Static Response handling.

In our example we have defined routing rules (based of URL it forwards request to service), filter for logging request url, method and time of request.
