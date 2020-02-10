# Coding Question

## DataStore - An interface providing moving averages for sequences of elements

Provides add, get and moving average methods for sequences of elements


## Compiling and Testing

This is a maven project, so the usual maven targets apply:
```
mvn clean test
```

## Updating maven repository

For development purposes, updating snaphots is necessary so changes are picked up by dependent projects. 
```
mvn clean install -U
or
mvn dependency:purge-local-repository clean package
```

# Design Question
Design A Google Analytic like Backend System. We need to provide Google Analytic like services to our customers. Please provide a high level solution design for the backend system. 

## Requirements

1. Handle large write volume: Billions of write events per day.

A cloud-based solution is recommended due to the scale required. Clustered microservices should be offered with a Cassandra back-end to ensure high throughput and scalability (https://academy.datastax.com/planet-cassandra/nosql-performance-benchmarks).

A read-write microservice operation is recommended to read / write analytics data persisting into the Cassandra cluster with a suitable replication factor via OpenTSDB (see below)

A search microservice operation is recommended to provide key stroke natural language search capability. This microservice will be backended by a search engine like ElasticSearch. It will be populated only with light metadata required for quick search.


2. Handle large read/query volume: Millions of merchants wish to gain insight into their business. Read/Query patterns are time-series related metrics.

The read-write microservice operation above will provide this functionality. OpenTSDB will provide optimization for time-series queries at scale.


3. Provide metrics to customers with at most one hour delay.

Kibana can be used for real time reporting and visualization of metric data. It is built on ElasticSearch and will provide low latency metric data with rich graph and report dashboards.


4. Run with minimum downtime.

Microservices and back-end systems will be setup in regional clusters with replication. These are used to cover multiple geographical regions and provide redundancy for extra consistency/trust assurance and backups.
Front-end regional load balancers will be used to distribute requests to different instances in each regional cluster.


5. Have the ability to reprocess historical data in case of bugs in the processing logic.

Some of the large Cassandra deployments have immense capacity, with a large public cluster having over 115k nodes and 10petabytes of data. The read-write microservice operation should have a historical counterpart, read-historical, so access historical data.
This historical data can then be ran through the same compute microservice used in real time read-write, to provide historical playback for querying and consistency determination.
