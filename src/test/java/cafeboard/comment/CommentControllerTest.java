//package cafeboard.comment;
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
//public class CommentControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @BeforeAll
//    public static void setup() {
//        RestAssured.baseURI = "http://localhost";
//    }
//
//    private String createBoard() {
//        return given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"name\":\"테스트 게시판\"}")
//                .when()
//                .post("/api/boards")
//                .then()
//                .statusCode(201)
//                .extract().path("id").toString();
//    }
//
//    private String createPost(String boardId) {
//        return given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"title\":\"테스트 게시글\",\"content\":\"테스트 내용입니다.\"}")
//                .when()
//                .post("/api/boards/" + boardId + "/posts")
//                .then()
//                .statusCode(201)
//                .extract().path("id").toString();
//    }
//
//    @Test
//    public void testCreateComment() {
//        String boardId = createBoard();
//        String postId = createPost(boardId);
//
//        given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"content\":\"테스트 댓글입니다.\"}")
//                .when()
//                .post("/api/posts/" + postId + "/comments")
//                .then()
//                .statusCode(201)
//                .body("content", equalTo("테스트 댓글입니다."));
//    }
//
//    @Test
//    public void testGetCommentsByPost() {
//        String boardId = createBoard();
//        String postId = createPost(boardId);
//
//        // 댓글 생성
//        given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"content\":\"테스트 댓글입니다.\"}")
//                .when()
//                .post("/api/posts/" + postId + "/comments")
//                .then()
//                .statusCode(201);
//
//        // 댓글 조회
//        given()
//                .port(port)
//                .when()
//                .get("/api/posts/" + postId + "/comments")
//                .then()
//                .statusCode(200)
//                .body("comments", hasSize(greaterThan(0)))
//                .body("comments[0].content", equalTo("테스트 댓글입니다."));
//    }
//}
