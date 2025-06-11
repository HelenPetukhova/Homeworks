package com.itacademy.aqa.utils;


import static io.restassured.RestAssured.given;

public class ApiCheckUrl {

    public static int getStatusCode(String url){
        return
                given()
                .get(url)
                .then()
                .extract()
                .statusCode();

    }

}
