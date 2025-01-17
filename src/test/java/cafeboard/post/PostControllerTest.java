//package cafeboard.post;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PostControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @BeforeAll
//    public static void setup() {
//        RestAssured.baseURI = "http://localhost";
//    }
//
//    @Test
//    public void testCreatePost() {
//        // 먼저 게시판을 생성합니다
//        String boardId = given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"name\":\"테스트 게시판\"}")
//                .when()
//                .post("/api/boards")
//                .then()
//                .statusCode(201)
//                .extract().path("id").toString();
//
//        // 게시글을 생성합니다
//        given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"title\":\"테스트 게시글\",\"content\":\"테스트 내용입니다.\"}")
//                .when()
//                .post("/api/boards/" + boardId + "/posts")
//                .then()
//                .statusCode(201)
//                .body("title", equalTo("테스트 게시글"))
//                .body("content", equalTo("테스트 내용입니다."));
//    }
//
//    @Test
//    public void testGetPostsByBoard() {
//        // 먼저 게시판을 생성합니다
//        String boardId = given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"name\":\"테스트 게시판\"}")
//                .when()
//                .post("/api/boards")
//                .then()
//                .statusCode(201)
//                .extract().path("id").toString();
//
//        // 게시글을 조회합니다
//        given()
//                .port(port)
//                .when()
//                .get("/api/boards/" + boardId + "/posts")
//                .then()
//                .statusCode(200);
//    }
//
//    // 추가적인 테스트 메소드 (updatePost, deletePost 등)를 여기에 작성할 수 있습니다.
//}
