package cafeboard.board;

import cafeboard.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "게시판 이름은 필수입니다.")
    private String name;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Post> posts;
}
