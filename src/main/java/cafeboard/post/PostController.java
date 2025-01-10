package cafeboard.post;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/boards/{boardId}/posts")
    public ResponseEntity<Post> createPost(@PathVariable Long boardId, @Valid @RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(boardId, post));
    }

    @GetMapping("/boards/{boardId}/posts")
    public ResponseEntity<Page<Post>> getPostsByBoard(@PathVariable Long boardId, Pageable pageable) {
        return ResponseEntity.ok(postService.getPostsByBoard(boardId, pageable));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable Long postId, @Valid @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(postId, post));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
