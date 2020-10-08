# pact-demo-provider

[![Build Status](https://travis-ci.com/shilgam/pact-demo-provider.svg?branch=master)](https://travis-ci.com/shilgam/pact-demo-provider)

[![Consumer(prod)/provider(prod) Pact Status](https://telegacom.pact.dius.com.au/matrix/provider/pact-demo-provider/latest/prod/consumer/pact-demo-consumer/latest/prod/badge.svg?initials=true)](https://telegacom.pact.dius.com.au/matrix?q[]pacticipant=pact-demo-consumer&q[]tag=prod&q[]latest=true&q[]pacticipant=pact-demo-provider&q[]tag=prod&q[]latest=true&latestby=cvpv&limit=100)
[![Consumer(prod)/provider(master) Pact Status](https://telegacom.pact.dius.com.au/matrix/provider/pact-demo-provider/latest/master/consumer/pact-demo-consumer/latest/prod/badge.svg?initials=true)](https://telegacom.pact.dius.com.au/matrix?q[]pacticipant=pact-demo-consumer&q[]tag=prod&q[]latest=true&q[]pacticipant=pact-demo-provider&q[]tag=master&q[]latest=true&latestby=cvpv&limit=100)
[![Can I deploy master to prod Status](https://telegacom.pact.dius.com.au/pacticipants/pact-demo-provider/latest-version/master/can-i-deploy/to/prod/badge)](https://telegacom.pact.dius.com.au/pacticipants/pact-demo-provider/latest-version/master/can-i-deploy/to/prod)

This is an example of a provider app (Java app) that uses Pact, [Pactflow](https://pactflow.io) and Travis CI to ensure that it is compatible with the expectations its consumers have of it.

## Prerequisites
* Java 8
* if you use [Pactflow](https://pactflow.io) as remote Pact Broker:
    - Make sure env var `LOCAL_PACT_BROKER=true` is not specified.
    - Make sure correct properties are specified in `gradle.properties` file: `pactBrokerUrl` and `pactBrokerToken`
* if you use local Pact Broker:
    - Make sure you [launched Pact Broker locally](https://github.com/shilgam/pact-demo-consumer/blob/master/README.md).
    - Make sure env var `LOCAL_PACT_BROKER=true` is set
    - Make sure correct properties are specified in `gradle.properties` file: `localpactBrokerUrl`, `localpactBrokerUsername`, `localpactBrokerPassword`.


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

        $ ./gradlew pactVerify -Ppact.verifier.publishResults=true
