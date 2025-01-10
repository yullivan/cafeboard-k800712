package cafeboard.comment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CommentApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://your-api-base-url";
    }

    @Test
    public void testCreateComment() {
        int postId = 1; // 존재하는 게시글 ID를 사용하세요
        String requestBody = "{ \"content\": \"테스트 댓글\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/posts/{postId}/comments", postId)
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("content", equalTo("테스트 댓글"))
                .body("postId", equalTo(postId));
    }

    @Test
    public void testUpdateComment() {
        int commentId = 1; // 존재하는 댓글 ID를 사용하세요
        String requestBody = "{ \"content\": \"수정된 댓글\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/api/comments/{commentId}", commentId)
                .then()
                .statusCode(200)
                .body("id", equalTo(commentId))
                .body("content", equalTo("수정된 댓글"));
    }

    @Test
    public void testDeleteComment() {
        int commentId = 1; // 존재하는 댓글 ID를 사용하세요

        when()
                .delete("/api/comments/{commentId}", commentId)
                .then()
                .statusCode(204);
    }

    @Test
    public void testGetCommentsForPost() {
        int postId = 1; // 존재하는 게시글 ID를 사용하세요

        when()
                .get("/api/posts/{postId}/comments", postId)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].id", notNullValue())
                .body("[0].content", notNullValue());
    }

    @Test
    public void testGetCommentsForPostWithPagination() {
        int postId = 1; // 존재하는 게시글 ID를 사용하세요

        given()
                .queryParam("page", 1)
                .queryParam("limit", 10)
                .when()
                .get("/api/posts/{postId}/comments", postId)
                .then()
                .statusCode(200)
                .body("postId", equalTo(postId))
                .body("postTitle", notNullValue())
                .body("comments", hasSize(lessThanOrEqualTo(10)))
                .body("pagination.currentPage", equalTo(1))
                .body("pagination.totalPages", greaterThan(0))
                .body("pagination.totalComments", greaterThan(0));
    }
}
