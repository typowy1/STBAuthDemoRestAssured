package jsonplaceholder;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;

public class UserTest {

    private Response response;

    @Test
    public void createNewUserWithDeserialization(){
        User user = new User();
        user.setName("Rafał Testowy");
        user.setUsername("TestTest");
        user.setEmail("test@wp.pl");
        user.setPhone("888-888-888");
        user.setWebsite("test.pl");

        Geo geo = new Geo();
        geo.setLat("-43.9509");
        geo.setLng("81.1496");

        Address address = new Address();
        address.setStreet("Testowa");
        address.setSuite("Apt. 1");
        address.setCity("Warsaw");
        address.setZipcode("88-098");
        address.setGeo(geo);
        user.setAddress(address);

        Company company = new Company();
        company.setName("FirmaTestowa");
        company.setCatchPhrase("Blablabla");
        company.setBs("Tak tak tak");
        user.setCompany(company);

        response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("https://jsonplaceholder.typicode.com/users")
                .then()
                .statusCode(201)
                .extract()
                .response();

        User actualUser = response.as(User.class);
        assertThat(actualUser.getName()).isEqualTo(user.getName());
    }
}
