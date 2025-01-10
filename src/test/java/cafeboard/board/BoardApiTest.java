//package cafeboard.board;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//
//public class BoardApiTest {
//
//    @BeforeAll
//    public static void setup() {
//        RestAssured.baseURI = "http://your-api-base-url";
//    }
//
//    @Test
//    public void testCreateBoard() {
//        String requestBody = "{ \"name\": \"테스트 게시판\" }";
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(requestBody)
//                .when()
//                .post("/api/boards")
//                .then()
//                .statusCode(201)
//                .body("id", notNullValue())
//                .body("name", equalTo("테스트 게시판"));
//    }
//
//    @Test
//    public void testGetAllBoards() {
//        when()
//                .get("/api/boards")
//                .then()
//                .statusCode(200)
//                .body("size()", greaterThan(0))
//                .body("[0].id", notNullValue())
//                .body("[0].name", notNullValue());
//    }
//
//    @Test
//    public void testUpdateBoard() {
//        int boardId = 1; // 존재하는 게시판 ID를 사용하세요
//        String requestBody = "{ \"name\": \"수정된 게시판 이름\" }";
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(requestBody)
//                .when()
//                .put("/api/boards/{boardId}", boardId)
//                .then()
//                .statusCode(200)
//                .body("id", equalTo(boardId))
//                .body("name", equalTo("수정된 게시판 이름"));
//    }
//
//    @Test
//    public void testDeleteBoard() {
//        int boardId = 1; // 존재하는 게시판 ID를 사용하세요
//
//        when()
//                .delete("/api/boards/{boardId}", boardId)
//                .then()
//                .statusCode(204);
//    }
//}
