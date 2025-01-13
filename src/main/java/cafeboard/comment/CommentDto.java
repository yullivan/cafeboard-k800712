package cafeboard.comment;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private String content;
    private Long postId;

    public CommentDto(Long id, String content, Long postId) {
        this.id = id;
        this.content = content;
        this.postId = postId;
    }
}