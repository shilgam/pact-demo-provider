PACTICIPANT := "pact-demo-provider"
PACT_CLI="docker run --rm -v ${PWD}:${PWD} -e PACT_BROKER_BASE_URL -e PACT_BROKER_TOKEN pactfoundation/pact-cli:0.16.3.0"

# Only deploy from master
ifeq ($(TRAVIS_BRANCH),master)
	DEPLOY_TARGET=deploy
else
	DEPLOY_TARGET=no_deploy
endif


## ====================
## local development tasks
## ====================

start:
	./gradlew bootRun

test:
	./gradlew test

verify:
	./gradlew pactVerify

verify_and_publish_normal_build_to_local_broker:
	LOCAL_PACT_BROKER=true make verify_and_publish_normal_build

verify_and_publish_webhook_build_to_local_broker:
	LOCAL_PACT_BROKER=true make verify_and_publish_webhook_build


## ====================
## CI tasks
## ====================

verify_and_publish_normal_build:
	## For 'normal' provider builds, fetch `master` and `prod` pacts for this provider
	./gradlew pactVerify -Ppact.verifier.publishResults=true

verify_and_publish_webhook_build:
	## For builds triggered by a webhook, just verify the changed pact.
	## The URL will have been passed in from the webhook to the CI job.
	./gradlew pactVerify -Ppact.verifier.publishResults=true -Ppact.provider.tag=master -Ppact.filter.pacturl=${PACT_URL}

deploy_to_prod: can_i_deploy $(DEPLOY_TARGET)


## =====================
## Deploy tasks
## =====================

deploy: deploy_app tag_as_prod

no_deploy:
	@echo "Not deploying as not on master branch"

can_i_deploy:
	"${PACT_CLI}" broker can-i-deploy \
		--pacticipant ${PACTICIPANT} \
		--version ${TRAVIS_COMMIT} \
		--retry-while-unknown 0 \
		--retry-interval 10 \
		--to prod

deploy_app:
	@echo ">>> Deploying to prod"

tag_as_prod:
	"${PACT_CLI}" broker create-version-tag \
	  --pacticipant ${PACTICIPANT} \
	  --version ${TRAVIS_COMMIT} \
	  --tag prod
