SpringBoot Eureka Github Template
=================================

This template provides a microservice starter using spring boot together with spring cloud eureka for service discovery.
It provides a sample openapi.yaml file in resources/static and uses the openapi-codegen-plugin to generate pojos and
API Interface. Make sure you run mvn clean package before heading to the SampleController and start your implementation.

Build Only (No Tests)
```bash
./mvnw clean package -DskipUTs=true
```

Build and Run Unit Tests
```bash
./mvnw clean package
```

Build and Run Integration Tests only
```bash
./mvnw clean verify -DskipUTs=true
```

Build and Run Unit Tests + Integration Tests
```bash
./mvnw clean verify
```

Run
```bash
./mvnw spring-boot:run
```

Uses [GoogleContainerJib](https://github.com/GoogleContainerTools/jib) to create Distroless Image

Docker Image
```bash
./mvnw compile jib:build
```

[Swagger-UI](http://localhost:8080/sample-service/swagger-ui.html)

[H2-Console](http://localhost:8080/sample-service/h2-console)

[Actuator](http://localhost:8080/sample-service/actuator)

Contributing
------------

Contributions to this library are always welcome and highly encouraged.

See `google-cloud`'s [CONTRIBUTING] documentation and the [shared documentation](https://github.com/googleapis/google-cloud-common/blob/master/contributing/readme.md#how-to-contribute-to-gcloud) for more information on how to get started.

Please note that this project is released with a Contributor Code of Conduct. By participating in this project you agree to abide by its terms. See [Code of Conduct][code-of-conduct] for more information.

License
-------

Apache 2.0 - See [LICENSE] for more information.


[CONTRIBUTING]:https://github.com/googleapis/google-cloud-java/blob/master/CONTRIBUTING.md
[code-of-conduct]:https://github.com/googleapis/google-cloud-java/blob/master/CODE_OF_CONDUCT.md
[LICENSE]: https://github.com/googleapis/google-cloud-java/blob/master/LICENSE