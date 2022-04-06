# Demo backend Pact example project using JUnit 5 and Springboot

This example is a really simple demonstration of the use of Pact in JUnit 5 tests.

Places where you need to make changes are marked with TODO comments

To run the tests:

```console
./gradlew check
```

The important files in this project:

`src/main/java/io/pact/example/junit/PactVerificationTest.java` - This is the pact verification test.

## Running the tests in your CI

Before running test - replace <<PACT_BROKER_URL>> in `application.yml` with valid pact broker's url

When running in your CI, you should enable publishing of verification results by setting the JVM system
properties `pact.provider.version`, `pact.provider.tag` and `pact.verifier.publishResults`.

|Property|Description|
|--------|-----------|
|`pact.provider.version`|Set this to your version from your CI build. You can use the Git SHA here.|
|`pact.provider.tag`|Set this to the branch in your Git branch.|
|`pact.verifier.publishResults`|Set this `true`.|


**REMEMBER to set the PACTFLOW_TOKEN environment variable with a valid token!**


