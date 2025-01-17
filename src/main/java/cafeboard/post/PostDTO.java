package cafeboard.post;

import cafeboard.member.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long boardId;


    public PostDTO(Long id, String title, String content, Member author,
                   LocalDateTime createdAt, LocalDateTime updatedAt, Long boardId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.boardId = boardId;
    }
    public static PostDTO fromEntity(Post post) {
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getBoard().getId()

        );
    }
}
