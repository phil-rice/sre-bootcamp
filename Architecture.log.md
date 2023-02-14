# Why Spring Boot for the microservices architecture?

Because it's familiar to a lot of people and representative of the world many SREs will be working in

# Why laoban for the build tool?

Because there are a lot of projects! And they all need to be built and deployed in a consistent way. We need a status
mechanism, and
an easy way to test things (in the right order)

# Why pact tests?

Because they are an essential part of a microservice's architecture. They are the only way to ensure that the contracts
between services are being met. They are also the only way to ensure that the contracts between services and the front
end are being met. Integration tests don't work reliably in a microservice's architecture. They are too slow, and most
importantly too brittle. Pact tests are fast.

# Why no shared execution code?

This is simulating a real world scenario. Typically, these microservices will be written by different teams, and will be
deployed by different teams. They will be written in different languages, and will have different dependencies. That's
complicated for us to model! But this is a first step towards that.

# Why shared testing code

Because the tests are for the development of this! Not really for the SRE teams.

# Why are the pact validation tests executed in 'mvn verify' instead of 'mvn test'

Running 'mvn test' will generate pact files that are used by the pact validation tests. The order matters!

# Why are we doing all the messing around with the pact-stub-server in the pact validation tests?

This is complicated: we are scanning ports, killing all processes that are listening on those ports, and then starting a
new pact-stub-server. When we do back to back pact tests (i.e. we are a provider that is also a consumer) then we use
the pacts that we have
generated to run the pact-stub-server. This ensures that we are using the same test-case all the way through the stack

The alternative is to use a better pact library (like the scala pact library) that avoids this mess. But it would
require us
to use a different language that our developers are probably not familiar with.

# Why is everything a blocking synchronous call instead of completablefutures?

Because this is like much of the real world! This is a thing we can 'spot' on the course.

# Why do we separate pacts/new and pacts/accepted

Two reasons

* We want to be notified when the pacts have changed: we can have a test validate that the pacts have not changed
* We have contention issues: mvn integration tests and tests run in parallel (Actually sometimes the integration tests
  run first) and intermittently this causes tests failures with file locks. This approach avoids those issues.

