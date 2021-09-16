package jsonplaceholder;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PostSerializacjaDesreializacjaTest {

    //{
    //"userId": 1,
    //"id": 1,
    //"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    //"body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
    //}// tworzymu obiekt do posta
    //        JSONObject post = new JSONObject();
    //        post.put("userId", 1);
    //        post.put("title", "Test");
    //        post.put("body", "Test test test");


    @Test
    public void createPostSerializacja(){ //zamiana klasy na json
        // tworzymu obiekt do posta który jest w klasie post w main/jasonplaceholder
//        POJO
//        Post post = new Post(1, "Test", "Test test tes");
        Post post = new Post();
        post.setUserId(1);
        post.setTitle("TEST");
        post.setBody("TEST test test");

        //POJO zamieniamy na JSON
        Response response = given()
                .contentType(ContentType.JSON)
                .body(post)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response();
        System.out.println(response.prettyPrint());
        JsonPath jsonPath = response.jsonPath();
        Assertions.assertThat(jsonPath.getString("userId")).isEqualTo(post.getUserId().toString());
        Assertions.assertThat(jsonPath.getString("title")).isEqualTo(post.getTitle());

    }

    @Test
    public void createPostDeserializacja(){ //zamiana json na clase, czyli dodamy pola jsona do pol klasy
//pobieramy jsona obiektu post, zaminiemy go na obiekt klasy Post, wartosć pól JSOn są przypisane do pól klasy


        Post post = given() //utworzył się obiekt naszej klasy
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/1")
                .as(Post.class);
        Assertions.assertThat(post.getTitle()).isEqualTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
    }
}
