package cafeboard.board;

import cafeboard.ResourceNotFoundException;
import cafeboard.post.Post;
import cafeboard.post.PostDTO;
import cafeboard.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    public BoardService(BoardRepository boardRepository, PostRepository postRepository) {
        this.boardRepository = boardRepository;
        this.postRepository = postRepository;
    }


    @Transactional
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<PostDTO> getBoardPosts(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));

        List<Post> posts = postRepository.findByBoardId(boardId);
        return posts.stream()
                .map(PostDTO::fromEntity)
                .collect(Collectors.toList());
    }



    @Transactional
    public Board updateBoard(Long id, Board boardDetails) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + id));
        board.setName(boardDetails.getName());
        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + id));

        // 게시판에 연관된 게시물 삭제
        board.getPosts().clear();

        boardRepository.delete(board);
    }

}

