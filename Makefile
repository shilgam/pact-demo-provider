PACTICIPANT := "pact-demo-provider"
PACT_CLI="docker run --rm -v ${PWD}:${PWD} -e PACT_BROKER_BASE_URL -e PACT_BROKER_TOKEN pactfoundation/pact-cli:0.16.3.0"

# Only deploy from master_v1
ifeq ($(TRAVIS_BRANCH),master_v1)
	DEPLOY_TARGET=deploy
else
	DEPLOY_TARGET=no_deploy
endif


## ====================
## local development tasks
## ====================

verify:
	./gradlew pactVerify


## ====================
## CI tasks
## ====================

verify_and_publish_ci:
ifdef PACT_URL
	## For builds triggered by a webhook, just verify the changed pact.
	## The URL will bave been passed in from the webhook to the CI job.
	$(info PACT_URL is defined)
	./gradlew pactVerify -Ppact.verifier.publishResults=true -Ppact.provider.tag=master_v1 -Ppact.filter.pacturl=${PACT_URL}
else
	## For 'normal' provider builds, fetch `master` and `prod` pacts for this provider
	$(info PACT_URL is not defined)
	./gradlew pactVerify -Ppact.verifier.publishResults=true
endif

deploy_to_prod: can_i_deploy $(DEPLOY_TARGET)

## =====================
## Deploy tasks
## =====================

deploy: deploy_app tag_as_prod

no_deploy:
	@echo "Not deploying as not on master_v1 branch"

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
