package com.edw;

import com.edw.entity.Blacklist;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * <pre>
 *     com.edw.CustomerRiskTest
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 18 Jan 2023 13:23
 */
@QuarkusTest
public class CustomerRiskTest {

    @BeforeAll
    @Transactional
    public static void init() {
        Blacklist.persist(new Blacklist("Blacklist User 01"));
    }

    @Test
    public void testLowRiskCustomer() {
        given()
                .body("{ \"name\":\"Regular User\",  \"age\": 25, \"salary\": 5000 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/customer_risk")
                .then()
                .statusCode(201)
                .body("'status'", equalTo("Loan is Approved"));

    }

    @Test
    public void testMediumRiskCustomer() {
        given()
                .body("{  \"name\":\"Regular User\",  \"age\": 15, \"salary\": 5000 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/customer_risk")
                .then()
                .statusCode(201)
                .body("'status'", equalTo("Loan is on Review"));
    }

    @Test
    public void testHighRiskCustomer() {
        given()
                .body("{  \"name\":\"Regular User\",  \"age\": 15, \"salary\": 300 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/customer_risk")
                .then()
                .statusCode(201)
                .body("'status'", equalTo("Loan is Rejected because Customer is High Risk"));
    }


    @Test
    public void testBlacklistCustomer() {
        given()
                .body("{  \"name\":\"Blacklist User 01\",  \"age\": 15, \"salary\": 300 }")
                .contentType(ContentType.JSON)
                .when()
                .post("/customer_risk")
                .then()
                .statusCode(201)
                .body("'status'", equalTo("Loan is Rejected because Customer is Blacklisted"));
    }

}
