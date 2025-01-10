//package cafeboard.post;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//
//public class PostApiTest {
//
//    @BeforeAll
//    public static void setup() {
//        RestAssured.baseURI = "http://your-api-base-url";
//    }
//
//    @Test
//    public void testCreatePost() {
//        int boardId = 1; // 존재하는 게시판 ID를 사용하세요
//        String requestBody = "{ \"title\": \"테스트 게시글\", \"content\": \"테스트 내용\" }";
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(requestBody)
//                .when()
//                .post("/api/boards/{boardId}/posts", boardId)
//                .then()
//                .statusCode(201)
//                .body("id", notNullValue())
//                .body("title", equalTo("테스트 게시글"))
//                .body("content", equalTo("테스트 내용"))
//                .body("boardId", equalTo(boardId));
//    }
//
//    @Test
//    public void testGetPostsForBoard() {
//        int boardId = 1; // 존재하는 게시판 ID를 사용하세요
//
//        when()
//                .get("/api/boards/{boardId}/posts", boardId)
//                .then()
//                .statusCode(200)
//                .body("size()", greaterThan(0))
//                .body("[0].id", notNullValue())
//                .body("[0].title", notNullValue())
//                .body("[0].commentCount", notNullValue());
//    }
//
//    @Test
//    public void testGetPostById() {
//        int postId = 1; // 존재하는 게시글 ID를 사용하세요
//
//        when()
//                .get("/api/posts/{postId}", postId)
//                .then()
//                .statusCode(200)
//                .body("id", equalTo(postId))
//                .body("title", notNullValue())
//                .body("content", notNullValue())
//                .body("boardId", notNullValue())
//                .body("comments", notNullValue());
//    }
//
//    @Test
//    public void testUpdatePost() {
//        int postId = 1; // 존재하는 게시글 ID를 사용하세요
//        String requestBody = "{ \"title\": \"수정된 게시글\", \"content\": \"수정된 내용\" }";
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(requestBody)
//                .when()
//                .put("/api/posts/{postId}", postId)
//                .then()
//                .statusCode(200)
//                .body("id", equalTo(postId))
//                .body("title", equalTo("수정된 게시글"))
//                .body("content", equalTo("수정된 내용"));
//    }
//
//    @Test
//    public void testDeletePost() {
//        int postId = 1; // 존재하는 게시글 ID를 사용하세요
//
//        when()
//                .delete("/api/posts/{postId}", postId)
//                .then()
//                .statusCode(204);
//    }
//
//    @Test
//    public void testGetPostsForBoardWithPagination() {
//        int boardId = 1; // 존재하는 게시판 ID를 사용하세요
//
//        given()
//                .queryParam("page", 1)
//                .queryParam("limit", 10)
//                .when()
//                .get("/api/boards/{boardId}/posts", boardId)
//                .then()
//                .statusCode(200)
//                .body("boardId", equalTo(boardId))
//                .body("boardName", notNullValue())
//                .body("posts", hasSize(lessThanOrEqualTo(10)))
//                .body("pagination.currentPage", equalTo(1))
//                .body("pagination.totalPages", greaterThan(0))
//                .body("pagination.totalPosts", greaterThan(0));
//    }
//}
