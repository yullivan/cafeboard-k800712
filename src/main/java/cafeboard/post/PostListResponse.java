package cafeboard.post;

public class PostListResponse {
    private Long id;
    private String title;
    private int commentCount;

    public PostListResponse(Long id, String title, int commentCount) {
        this.id = id;
        this.title = title;
        this.commentCount = commentCount;
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

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}
