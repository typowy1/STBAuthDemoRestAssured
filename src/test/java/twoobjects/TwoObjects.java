package twoobjects;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jsonplaceholder.Address;
import jsonplaceholder.Company;
import jsonplaceholder.Geo;
import jsonplaceholder.User;
import jsonplaceholder.twoobjects.TOAddress;
import jsonplaceholder.twoobjects.TOCompany;
import jsonplaceholder.twoobjects.TOGeo;
import jsonplaceholder.twoobjects.TOUser;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TwoObjects {
    private Response response;

    @Test
    public void createNewUser() {
        TOUser toUser = new TOUser();
        toUser.setName("Testowy");

        TOGeo firstGeo = new TOGeo();
        firstGeo.setLat("-43.9509");
        firstGeo.setLng("81.1496");

        TOGeo secondGeo = new TOGeo();
        secondGeo.setLat("-555555");
        secondGeo.setLng("77777");

        List<TOGeo> toGeos = new ArrayList<>();
        toGeos.add(firstGeo);
        toGeos.add(secondGeo);

        TOAddress toAddress = new TOAddress();
        toAddress.setStreet("Testowa");
        toAddress.setSuite("Apt. 1");
        toAddress.setCity("Warsaw");
        toAddress.setZipcode("88-098");
        toAddress.setToGeo(toGeos.get(0));
        toUser.setToAddress(toAddress);

        TOCompany toCompany = new TOCompany();
        toCompany.setName("FirmaTestowa");
        toCompany.setCatchPhrase("Blablabla");
        toCompany.setBs("Tak tak tak");
        toCompany.setToGeo(toGeos.get(1));

        toUser.setToCompany(toCompany);
        response = given()
                .contentType(ContentType.JSON)
                .body(toUser)
                .when()
                .post("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(201)
                .extract()
                .response();

        TOUser actualUser = response.as(TOUser.class);
        assertThat(actualUser.getToAddress().getToGeo().getLat()).isEqualTo(toUser.getToAddress().getToGeo().getLat());
    }


//    {
//        "id":1
//        "name: :testowe",
//                "address":{
//                "street":"Kulas Light",
//                "suite":"Apt. 556",
//                "city":"Gwenborough",
//                "zipcode":"92998-3874",
//                "geo":{
//                    "lat":"-37.3159",
//                    "lng":"81.1496"
//        }
//        "company":{
//                "name":"Romaguera-Crona",
//                "catchPhrase":"Multi-layered client-server neural-net",
//                "bs":"harness real-time e-markets",
//                "geo":{
//                    "lat":"-9999999",
//                    "lng":"9999999"
//        }
//    }

}
