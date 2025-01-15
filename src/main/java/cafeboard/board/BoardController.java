package cafeboard.board;

import cafeboard.ResourceNotFoundException;
import cafeboard.post.PostDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@Valid @RequestBody BoardRequest boardRequest) {
        Board board = new Board();
        board.setName(boardRequest.getName());
        Board createdBoard = boardService.createBoard(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    @GetMapping("/{boardId}/posts")
    public ResponseEntity<?> getBoardPosts(@PathVariable Long boardId) {
        try {
            List<PostDTO> posts = boardService.getBoardPosts(boardId);
            return ResponseEntity.ok(posts);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getErrorResponse());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResourceNotFoundException.ErrorResponse("게시글 목록을 불러오는데 실패했습니다."));
        }
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long boardId, @Valid @RequestBody Board board) {
        return ResponseEntity.ok(boardService.updateBoard(boardId, board));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardId) {
        try {
            boardService.deleteBoard(boardId);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시판 삭제 중 오류가 발생했습니다.");
        }
    }

}

