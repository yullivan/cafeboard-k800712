//package cafeboard.board;
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
//public class BoardControllerTest {
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
//    public void testCreateBoard() {
//        given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"name\":\"테스트 게시판\"}")
//                .when()
//                .post("/api/boards")
//                .then()
//                .statusCode(201)
//                .body("name", equalTo("테스트 게시판"));
//    }
//
//    @Test
//    public void testGetAllBoards() {
//        given()
//                .port(port)
//                .when()
//                .get("/api/boards")
//                .then()
//                .statusCode(200)
//                .body("size()", greaterThan(0));
//    }
//
//    @Test
//    public void testUpdateBoard() {
//        // 먼저 게시판을 생성합니다
//        String boardId = given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"name\":\"수정 전 게시판\"}")
//                .when()
//                .post("/api/boards")
//                .then()
//                .statusCode(201)
//                .extract().path("id").toString();
//
//        // 생성된 게시판을 수정합니다
//        given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"name\":\"수정 후 게시판\"}")
//                .when()
//                .put("/api/boards/" + boardId)
//                .then()
//                .statusCode(200)
//                .body("name", equalTo("수정 후 게시판"));
//    }
//
//    @Test
//    public void testDeleteBoard() {
//        // 먼저 게시판을 생성합니다
//        String boardId = given()
//                .port(port)
//                .contentType(ContentType.JSON)
//                .body("{\"name\":\"삭제할 게시판\"}")
//                .when()
//                .post("/api/boards")
//                .then()
//                .statusCode(201)
//                .extract().path("id").toString();
//
//        // 생성된 게시판을 삭제합니다
//        given()
//                .port(port)
//                .when()
//                .delete("/api/boards/" + boardId)
//                .then()
//                .statusCode(204);
//    }
//}
