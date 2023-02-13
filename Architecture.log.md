Why Spring Boot for the microservices architecture?
   Because it's familiar to a lot of people and representative of the world many SREs will be working in

Why laoban for the build tool?
   Because there are a lot of projects! And they all need to be built and deployed in a consistent way. We need a status mechanism, and
   an easy way to test things (in the right order)

Why pact tests?
   Because they are an essential part of a microservice's architecture. They are the only way to ensure that the contracts between
   services are being met. They are also the only way to ensure that the contracts between services and the front end are being met.
   Integration tests don't work reliably in a microservice's architecture. They are too slow, and most importantly too brittle. 
   Pact tests are fast.

Why no shared execution code?
   This is simulating a real world scenario. Typically, these microservices will be written by different teams, and will be deployed
   by different teams. They will be written in different languages, and will have different dependencies. That's complicated for us
   to model! But this is a first step towards that.

Why shared testing code
   Because the tests are for the development of this! Not really for the SRE teams. 

Why are the pact validation tests executed in 'mvn verify' instead of 'mvn test'
   Running 'mvn test' will generate pact files that are used by the pact validation tests. The order matters!


