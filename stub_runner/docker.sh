#!/usr/bin/env bash

# Provide the Spring Cloud Contract Docker version
SC_CONTRACT_DOCKER_VERSION="2.0.1.RELEASE"

# Spring Cloud Contract Stub Runner properties
STUBRUNNER_PORT="8083"

# Stub coordinates 'groupId:artifactId:version:classifier:port'
STUBRUNNER_IDS="com.example:the-legacy-app-stubs-with-proxy:+:stubs:8765"

# Would have to pass it if the stubs were in a remote location
#STUBRUNNER_REPOSITORY_ROOT="http://repo.spring.io/libs-milestone-local"
#STUBRUNNER_STUBS_MODE="REMOTE"

# We pass a volume with our local .m2 to fetch local stubs
# "${HOME}/.m2/:/root/.m2:ro"

# Run the docker with Stub Runner Boot
docker run  --rm \
-e "STUBRUNNER_IDS=${STUBRUNNER_IDS}" \
-e "STUBRUNNER_STUBS_MODE=LOCAL" \
-p "${STUBRUNNER_PORT}:${STUBRUNNER_PORT}" \
-p "8765:8765" \
-v "${HOME}/.m2/:/root/.m2:ro" \
springcloud/spring-cloud-contract-stub-runner:"${SC_CONTRACT_DOCKER_VERSION}"
