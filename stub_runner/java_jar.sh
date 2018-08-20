#!/usr/bin/env bash
export SC_CONTRACT_VERSION="${SC_CONTRACT_VERSION:-2.0.1.RELEASE}"
mkdir -p target
wget -O target/stub-runner.jar "https://search.maven.org/remotecontent?filepath=org/springframework/cloud/spring-cloud-contract-stub-runner-boot/${SC_CONTRACT_VERSION}/spring-cloud-contract-stub-runner-boot-${SC_CONTRACT_VERSION}.jar"
java -jar target/stub-runner.jar --stubrunner.stubsMode="LOCAL" --stubrunner.ids="com.example:the-legacy-app-stubs-with-proxy:+:stubs:8765"