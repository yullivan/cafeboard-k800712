package cafeboard.board;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cafeboard.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAllWithPosts();
    }

    public Board getBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("게시판을 찾을 수 없습니다. ID: " + id));
    }

    @Transactional
    public Board createBoard(BoardRequest request) {
        Board board = new Board();
        board.setName(request.getName());
        board.setDescription(request.getDescription());
        return boardRepository.save(board);
    }

    @Transactional
    public Board updateBoard(Long id, BoardRequest request) {
        Board board = getBoard(id);
        board.setName(request.getName());
        board.setDescription(request.getDescription());
        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = getBoard(id);
        boardRepository.delete(board);
    }
}