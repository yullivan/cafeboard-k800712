package cafeboard.post;

import cafeboard.comment.Comment;

import java.time.LocalDateTime;
import java.util.List;

public class PostDetailResponse {
    private Long id;
    private String title;
    private String content;
    private Long boardId;
    private List<Comment> comments;

    public PostDetailResponse() {
    }

    public PostDetailResponse(Long id, String title, String content, Long boardId, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.comments = comments;
    }

    public static PostDetailResponse from(Post post) {
        List<CommentResponse> commentResponses = post.getComments().stream()
                .map(CommentResponse::from)
                .toList();

        return new PostDetailResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getBoard().getId(),
                post.getComments()
        );
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
class CommentResponse {
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponse(Long id, String content, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
