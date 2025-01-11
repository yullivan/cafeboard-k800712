package cafeboard.member;

import cafeboard.comment.Comment;
import cafeboard.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "사용자 이름은 필수입니다.")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Email(message = "유효한 이메일 주소를 입력해주세요.")
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "사용자 이름은 필수입니다.") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "사용자 이름은 필수입니다.") String username) {
        this.username = username;
    }

    public @NotBlank(message = "비밀번호는 필수입니다.") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "비밀번호는 필수입니다.") String password) {
        this.password = password;
    }

    public @Email(message = "유효한 이메일 주소를 입력해주세요.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "유효한 이메일 주소를 입력해주세요.") String email) {
        this.email = email;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
