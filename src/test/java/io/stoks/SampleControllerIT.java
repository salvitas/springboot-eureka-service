package io.stoks;

import io.generated.stoks.model.AliasUpdate;
import io.generated.stoks.model.Samplereq;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.sleuth.brave.LocalServiceName;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SampleControllerIT {

    @LocalServerPort
    private int port;

    @LocalServiceName
    private String serviceName;

    private String insertedId;


    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        RestAssured.basePath = serviceName;
    }

    @Test
    @Order(1)
    public void whenPosting_thenCreated() {
        Samplereq samplereq = new Samplereq();
        samplereq.setName("test");
        samplereq.setCurrency("USD");
        samplereq.setFavorite(true);
        samplereq.setVisible(false);

        insertedId = given()
                .contentType(ContentType.JSON)
                .body(samplereq)
                .when()
                .post("/samples")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .body("$", hasKey("id"))
                .extract()
                .path("id");

    }

    @Test
    @Order(2)
    public void whenGetting_thenOk() {
        get("/samples")
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat()
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(3)
    public void whenUpdating_thenNoContent() {
        AliasUpdate aliasUpdate = new AliasUpdate();
        aliasUpdate.setAlias("ALIAS");

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", insertedId)
                .body(aliasUpdate)
                .when()
                .put("/samples/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    @Order(4)
    public void whenValidateResponseTime_thenSuccess() {
        String aliasValue = given()
                .contentType(ContentType.JSON)
                .pathParam("id", insertedId)
                .when()
                .get("/samples/{id}")
                .then()
                .assertThat()
                .time(lessThan(100L))
                .extract()
                .path("alias");

        assertThat(aliasValue).isEqualTo("ALIAS");

    }

}
