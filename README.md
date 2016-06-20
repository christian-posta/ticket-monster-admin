# TicketMonster - a JBoss example

TicketMonster is an online ticketing demo application that gets you started with JBoss technologies, and helps you learn and evaluate them.

Here are a few instructions for building and running it. You can learn more about the example from the [tutorial](http://www.jboss.org/ticket-monster).

## Build and run locally

```
mvn clean install
```

This will build our application into a fat-jar that gets bootstrapped with [WildFly Swarm](http://wildfly-swarm.io). This can be run with a simple `java -jar` command line because all of its dependencies and classpath modules are included in the uber jar. For example, after running `mvn install` you can run the microservice like this:

```
java -jar target/ticket-monster-admin-swarm.jar 
```

If you're changing this often, these two steps may not be ideal. You can run it with one command:

```
mvn wildfly-swarm:run
```

## Ticket Monster with Docker

To run with docker:

```
mvn clean package -Pdefault,f8-build docker:build
docker run -it --rm -p 8080:8080 -e AB_OFF=true fabric8/ticket-monster-admin:1.0-SNAPSHOT
```

Can port forward to your local machine from vagrant like this:

> vagrant ssh -- -vnNTL *:8080:$DOCKER_HOST_IP:8080


## Ticket Monster on Kubernetes

```
mvn -Pf8-build fabric8:apply
```

## Running with a MySQL backend:

```
mvn clean package -Pmysql,f8-build docker:build
```