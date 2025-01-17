package cafeboard.post;

import cafeboard.board.Board;
import cafeboard.comment.Comment;
import cafeboard.member.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    @Column(columnDefinition = "TEXT")
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Member author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    // 게시판 ID 조회 편의 메서드
    public Long getBoardId() {
        return board != null ? board.getId() : null;
    }

    // 댓글 수 조회 편의 메서드
    public int getCommentCount() {
        return comments != null ? comments.size() : 0;
    }

    // 댓글 추가 편의 메서드
    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setPost(this);
    }

    // 댓글 제거 편의 메서드
    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setPost(null);
    }
}