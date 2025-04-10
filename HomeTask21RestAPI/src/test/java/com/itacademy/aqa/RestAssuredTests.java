package com.itacademy.aqa;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class RestAssuredTests {
    @Test
    public void getShouldReturn200() {

        // Проверка ответа get запроса (200)
        given()             // начало настройки запроса
                .log().all()        //выводит в консоль весь запрос: заголовки, тело, путь
                .get("https://jsonplaceholder.typicode.com/posts")       //Отправляет Get-запрос на указанный URL
                .then()
                .statusCode(200);                //проверка (assertion): ожидается, что сервер вернёт HTTP 200 OK

    }

    @Test
    public void getHeaderTest() {

        // Проверка header в ответе get запросa
        String contentTypeHeader = given()       // начало настройки запроса
                .log().all()                    //выводит в консоль весь запрос: заголовки, тело, путь
                .get("https://jsonplaceholder.typicode.com/posts")       //Отправляет GET-запрос на указанный URL
                .then()
                .statusCode(200)                //проверка (assertion): ожидается, что сервер вернёт HTTP 200 OK
                .extract().response().header("Content-type");  //После выполнения запроса — извлекает весь заголовок ответа

        System.out.println(contentTypeHeader);
        Assert.assertEquals("application/json; charset=utf-8", contentTypeHeader);  //Проверка content-type из заголовка

    }

    @Test
    public void getBodyTest() {

        Response response =
                given()              // начало настройки запроса
                        .log().all()        //выводит в консоль весь запрос: заголовки, тело, путь
                        .get("https://jsonplaceholder.typicode.com/posts")       //Отправляет GET-запрос на указанный URL
                        .then()
                        .statusCode(200)     //проверка (assertion): ожидается, что сервер вернёт HTTP 200 OK
                        .extract().response();  //После выполнения запроса — извлекает весь Response (объект с телом, заголовками, статусом и т.д.)

        System.out.println(response.prettyPrint());
        int listSize = response
                .jsonPath()             // позволяет обращаться к частям JSON-ответа
                .getList("$")       //возвратить весь массив
                .size();                //считаем количество элементов

        System.out.println(listSize);
        Assert.assertEquals(100, listSize);

    }

    @Test
    public void postJsonBodyTest() {

        //Сохраняем Map для json
        Map<String, Object> bodyJson = new HashMap<>();
        bodyJson.put("title", "foo");
        bodyJson.put("body", "bar");
        bodyJson.put("userId", 1);

        PostResponse response =
                given()                     // начало настройки запроса
                        .log().all()        //выводит в консоль весь запрос: заголовки, тело, путь
                        .contentType(ContentType.JSON) // Добавляет заголовок запроса Content-Type: ContentType.JSON
                        .body(bodyJson)              // Устанавливает тело запроса (json)
                        .post("https://jsonplaceholder.typicode.com/posts")       //Отправляет POST-запрос на указанный URL
                        .then()
                        .statusCode(201)                //проверка (assertion): ожидается, что сервер вернёт HTTP 200 OK
                        .extract().response().body().as(PostResponse.class);  //После выполнения запроса — извлекает Response body as PostResponse объект


        // Проверяем, что title, body и userId не пустые
        Assert.assertTrue("Title shouldn't be empty", response.getTitle() != null && !response.getTitle().isEmpty());
        Assert.assertTrue("Body shouldn't be empty", response.getBody() != null && !response.getBody().isEmpty());
        Assert.assertTrue("UserId shouldn't be empty", response.getUserId() != null && !response.getUserId().isEmpty());

        // Выводим все поля, включая id
        System.out.println("Id: " + response.getId());
        System.out.println("Title: " + response.getTitle());
        System.out.println("Body: " + response.getBody());
        System.out.println("UserId: " + response.getUserId());

    }


    @Test
    public void putUpdateJsonBodyTest() {

        //Сохраняем Map для json
        Map<String, Object> bodyJson = new HashMap<>();
        bodyJson.put("id", 1);
        bodyJson.put("title", "foo");
        bodyJson.put("body", "bar");
        bodyJson.put("userId", 1);

        Response response =
                given()                      // начало настройки запроса
                        .log().all()        //выводит в консоль весь запрос: заголовки, тело, путь
                        .contentType(ContentType.JSON) // Добавляет заголовок запроса Content-Type: ContentType.JSON
                        .body(bodyJson)              // Устанавливает тело запроса (json)
                        .put("https://jsonplaceholder.typicode.com/posts/1")       //Отправляет PUT-запрос на указанный URL
                        .then()
                        .statusCode(200)                //проверка (assertion): ожидается, что сервер вернёт HTTP 200 OK
                        .extract().response();  //После выполнения запроса — извлекает весь Response (объект с телом, заголовками, статусом и т.д.)

        // Id
        int id = response.jsonPath().getInt("id");
        System.out.println("Id is " + id);

        // Проверяем, что полe id обновилось на 1
        Assert.assertEquals("Id was not updated", 1, id);

    }

    @Test
    public void deleteJsonBodyTest() {


                given()             // начало настройки запроса
                        .log().all()        //выводит в консоль весь запрос: заголовки, тело, путь
                        .delete("https://jsonplaceholder.typicode.com/posts/1")       //Отправляет DELETE-запрос на указанный URL
                        .then()
                        .statusCode(200)                //проверка (assertion): ожидается, что сервер вернёт HTTP 200 OK
                        .extract().response();  //После выполнения запроса — извлекает весь Response (объект с телом, заголовками, статусом и т.д.)

    }

    @Test
    public void getSchemaTest() {
        given()             // начало настройки запроса
                .log().all()        //выводит в консоль весь запрос: заголовки, тело, путь
                .get("https://jsonplaceholder.typicode.com/posts")       //Отправляет Get-запрос на указанный URL
                .then().assertThat()
                .body(matchesJsonSchemaInClasspath("get-all-schema.json"));  // json схема - можно сгенерировать на основании ответа

    }

    @Test
    public void postSchemaTest() {

        //Сохраняем Map для json
        Map<String, Object> bodyJson = new HashMap<>();
        bodyJson.put("title", "foo");
        bodyJson.put("body", "bar");
        bodyJson.put("userId", 1);


        given()             // начало настройки запроса
                .log().all()        //выводит в консоль весь запрос: заголовки, тело, путь
                .contentType(ContentType.JSON) // Добавляет заголовок запроса Content-Type: ContentType.JSON
                .body(bodyJson)              // Устанавливает тело запроса (json)
                .post("https://jsonplaceholder.typicode.com/posts")       //Отправляет POST-запрос на указанный URL
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("post-schema.json"));  //проверка на соответствие схеме

    }

    @Test
    public void putSchemaTest() {

        //Сохраняем Map для json
        Map<String, Object> bodyJson = new HashMap<>();
        bodyJson.put("id", 1);
        bodyJson.put("title", "foo");
        bodyJson.put("body", "bar");
        bodyJson.put("userId", 1);

        given()                 // начало настройки запроса
                .log().all()        //выводит в консоль весь запрос: заголовки, тело, путь
                .contentType(ContentType.JSON) // Добавляет заголовок запроса Content-Type: ContentType.JSON
                .body(bodyJson)              // Устанавливает тело запроса (json)
                .put("https://jsonplaceholder.typicode.com/posts/1")       //Отправляет PUT-запрос на указанный URL
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("put-schema.json")); //проверка на соответствие схеме

    }

    @Test
    public void deleteSchemaTest() {


        given()                      // начало настройки запроса
                .log().all()        //выводит в консоль весь запрос: заголовки, тело, путь
                .delete("https://jsonplaceholder.typicode.com/posts/1")       //Отправляет DELETE-запрос на указанный URL
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("delete-schema.json")); //проверка на соответствие схеме

    }
}