package cafeboard.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private int commentCount;

    public PostResponse(Long id, String title, String content, int commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
    }

    public static PostResponse from(Post updatedPost) {
        if (updatedPost == null) {
            return null;
        }

        return new PostResponse(
                updatedPost.getId(),
                updatedPost.getTitle(),
                updatedPost.getContent(),
                updatedPost.getCommentCount() // 댓글 수를 가져오는 메서드 호출
        );
    }
}