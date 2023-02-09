
Needed tools:

To compile/build you can do everything manually (hard) or use laoban

For the pact broker you need this:
https://github.com/pact-foundation/pact-ruby-standalone/releases

Read this
https://docs.pact.io/pact_broker/client_cli/readme

Then set up env variables:
* PACT_BROKER_BASE_URL to https://validoc.pactflow.io
* PACT_BROKER_TOKEN (with your token)
* pactbroker.auth.token (with your token)  - and yes you have to enter your token twice
* pact.verifier.publishResults=true


To publish a pact

```shell
pact-broker publish .\pacts --consumer-app-version 1.0.0 --branch master  
```