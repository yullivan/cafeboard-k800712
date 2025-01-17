package cafeboard.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public class PostListResponse {
    private Long id;
    private String title;
    private String content;
    private int commentCount;

    public PostListResponse(Long id, String title, String content, int commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
    }
}




