package cafeboard.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/boards")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        List<BoardResponse> response = boards.stream()
                .map(BoardResponse::from) // BoardResponse.from 메서드를 사용하여 변환
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@Valid @RequestBody BoardRequest request) {
        Board board = boardService.createBoard(request);
        return ResponseEntity.ok(BoardResponse.from(board));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable Long boardId) {
        Board board = boardService.getBoard(boardId);
        return ResponseEntity.ok(BoardResponse.from(board));
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable Long boardId,
            @Valid @RequestBody BoardRequest request) {
        Board board = boardService.updateBoard(boardId, request);
        return ResponseEntity.ok(BoardResponse.from(board));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }
}
