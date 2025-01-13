package cafeboard.post;

public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private Long boardId;

    public PostResponse(Long id, String title, String content, Long boardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
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
}