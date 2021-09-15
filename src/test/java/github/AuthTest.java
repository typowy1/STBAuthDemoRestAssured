package github;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class AuthTest {
    private static final String TOKEN = "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";

    @Test
    public void basicAuth(){ //login i hasło
        given()
                .auth()
                .preemptive()
                //odrazu wtstłamy uzytkownika i hasło nie czekamy na zwrot zapytania
                .basic("użytkownik", "hasło")
                .when()
                .get("https://api.github.com/user")
                .then()
                .statusCode(200);
    }

//    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

    @Test
    public void bearerToken(){
        Response response = given()
                .headers("Authorization", "Bearer " + TOKEN) //w nagłowki wrzucamy token
                .get("https://api.github.com/user")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(response.prettyPrint());
    }

    @Test
    public void oAuth2(){
        Response response = given()
                .auth()
                .oauth2(TOKEN)
                .get("https://api.github.com/user")
                .then()
                .statusCode(200)
                .extract()
                .response();
        System.out.println(response.prettyPrint());
    }
}
