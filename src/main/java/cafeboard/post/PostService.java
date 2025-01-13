package cafeboard.post;

import cafeboard.ResourceNotFoundException;
import cafeboard.board.Board;
import cafeboard.board.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public PostService(PostRepository postRepository, BoardRepository boardRepository) {
        this.postRepository = postRepository;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public Post createPost(Long boardId, PostRequest postRequest) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setBoard(board);
        return postRepository.save(post);
    }

    public List<Post> getPostsByBoard(Long boardId) {
        return postRepository.findByBoardId(boardId);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }

    @Transactional
    public Post updatePost(Long id, PostRequest postRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
        postRepository.delete(post);
    }
}
