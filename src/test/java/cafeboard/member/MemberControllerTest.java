package cafeboard.member;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemberControllerTest {

    @LocalServerPort
    private int port;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testSignup() {
        given()
                .port(port)
                .contentType(ContentType.JSON)
                .body("{\"username\":\"testuser\",\"password\":\"password123\",\"email\":\"test@example.com\"}")
                .when()
                .post("/api/members/signup")
                .then()
                .statusCode(200)
                .body("username", equalTo("testuser"))
                .body("email", equalTo("test@example.com"));
    }

    @Test
    public void testLogin() {
        // 먼저 회원가입을 합니다
        given()
                .port(port)
                .contentType(ContentType.JSON)
                .body("{\"username\":\"loginuser\",\"password\":\"password123\",\"email\":\"login@example.com\"}")
                .when()
                .post("/api/members/signup")
                .then()
                .statusCode(200);

        // 로그인을 테스트합니다
        given()
                .port(port)
                .contentType(ContentType.JSON)
                .body("{\"username\":\"loginuser\",\"password\":\"password123\"}")
                .when()
                .post("/api/members/login")
                .then()
                .statusCode(200)
                .body("username", equalTo("loginuser"));
    }

    @Test
    public void testDeleteMember() {
        // 먼저 회원가입을 합니다
        String memberId = given()
                .port(port)
                .contentType(ContentType.JSON)
                .body("{\"username\":\"deleteuser\",\"password\":\"password123\",\"email\":\"delete@example.com\"}")
                .when()
                .post("/api/members/signup")
                .then()
                .statusCode(200)
                .extract().path("id").toString();

        // 회원을 삭제합니다
        given()
                .port(port)
                .when()
                .delete("/api/members/" + memberId)
                .then()
                .statusCode(204);
    }
}
