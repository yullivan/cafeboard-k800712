package cafeboard.board;

import cafeboard.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "게시판 이름은 필수입니다.")
    private String name;

    private String description;  // 게시판 설명 추가

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 시간 추가

    @UpdateTimestamp
    private LocalDateTime updatedAt; // 업데이트 시간 추가

    public int getPostCount() {
        return posts.size();
    }
}
