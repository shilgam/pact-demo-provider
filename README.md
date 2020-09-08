# pact-demo-provider

[![Build Status](https://travis-ci.com/shilgam/pact-demo-provider.svg?branch=master)](https://travis-ci.com/shilgam/pact-demo-provider)

This is an example of a provider app (Java app) that uses Pact, [Pactflow](https://pactflow.io) and Travis CI to ensure that it is compatible with the expectations its consumers have of it.

## Prerequisites
* Java 8

## Usage

1. Clone the repo

1. Run the application

        $ ./gradlew bootRun

1. Use the application

        $ curl -v localhost:8080/employees

        $ curl -v localhost:8080/employees/99

        $ curl -X POST localhost:8080/employees -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "gardener"}'

        $ curl -X PUT localhost:8080/employees/3 -H 'Content-type:application/json' -d '{"name": "Samwise Gamgee", "role": "ring bearer"}'

        $ curl -X DELETE localhost:8080/employees/3


### Run the test suite

1. Run unit and integration tests:

        $ ./gradlew test
