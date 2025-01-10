package cafeboard;

import cafeboard.comment.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "사용자 이름은 필수입니다.")
    private String username;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;
}
