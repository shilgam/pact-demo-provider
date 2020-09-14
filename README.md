# pact-demo-provider

[![Build Status](https://travis-ci.com/shilgam/pact-demo-provider.svg?branch=master)](https://travis-ci.com/shilgam/pact-demo-provider)

This is an example of a provider app (Java app) that uses Pact, [Pactflow](https://pactflow.io) and Travis CI to ensure that it is compatible with the expectations its consumers have of it.

## Prerequisites
* Java 8
* Make sure that properties `pactBrokerUrl` and `pactBrokerToken` are specified in `gradle.properties` file

## Usage

1. Clone the repo

1. Run the application

        $ ./gradlew bootRun

1. Use the application

        $ curl -v localhost:8080/products

        $ curl -v localhost:8080/products/1

        $ curl -X POST localhost:8080/products -H 'Content-type:application/json' -d '{"type":"CREDIT_CARD","name":"Gem Master","version":"v3"}'

        $ curl -X PUT localhost:8080/products/3 -H 'Content-type:application/json' -d '{"type":"DEBIT_CARD","name":"Gem Visa","version":"v1"}'

        $ curl -X DELETE localhost:8080/products/3


### Run the test suite

1. Run unit and integration tests

        $ ./gradlew test


1. Verify provider app

        $ ./gradlew pactVerify

1. Verify provider app and publish results back to Pact Broker

        $ /gradlew pactVerify -Ppact.verifier.publishResults=true
