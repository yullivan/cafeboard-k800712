package cafeboard.board;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequest {
    @NotBlank(message = "게시판 이름은 필수입니다.")
    private String name;

    private String description;
}