package trello.trellozadaniedomowe;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import trello.Organization;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class HomeworkOrganizationTest {


    protected static final String KEY = "";
    protected static final String TOKEN = "";

    private static Stream<Arguments> createOrganizationData() {
        return Stream.of(

                Arguments.of("Diplay name", "Testowe", "imie testowe", "htt://www.testowe.com"),
                Arguments.of("Diplay name", "Testowe", "IMIE", "https://www.testowe.com"),
                Arguments.of("Diplay name", "Testowe", "im", "https://www.testowe.com"),
                Arguments.of("Diplay name", "Testowe", ":::", "https://www.testowe.com"),
                Arguments.of("Diplay name", "Testowe", "!!!", "https://www.testowe.com"));
    }

    @DisplayName("Create organization with valida data")
    @ParameterizedTest(name = "Display name: {0}, desc: {1}, name: {2}, website: {3}")
    @MethodSource("createOrganizationData")
    public void createOrganizationWithInvalidData(String displayName, String desc, String name, String website) {
        Organization organization = new Organization();

        organization.setDisplayName(displayName);
        organization.setDesc(desc);
        organization.setName(name);
        organization.setWebsite(website);

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .queryParam("displayName", organization.getDisplayName())
                .queryParam("desc", organization.getDesc())
                .queryParam("name", organization.getName())
                .queryParam("website", organization.getWebsite())
                .when()
                .post("https://api.trello.com/1/organizations")
                .then()
                .statusCode(400)
                .extract()
                .response();

        JsonPath json = response.jsonPath();
        assertThat(json.getString("displayName")).isEqualTo(organization.getDisplayName());
    }
}