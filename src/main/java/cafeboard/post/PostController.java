package cafeboard.post;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/boards/{boardId}/posts")
    public ResponseEntity<PostResponse> createPost(@PathVariable Long boardId, @Valid @RequestBody PostRequest postRequest) {
        Post createdPost = postService.createPost(boardId, postRequest);
        PostResponse response = new PostResponse(createdPost.getId(), createdPost.getTitle(), createdPost.getContent(), createdPost.getBoardId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/boards/{boardId}/posts")
    public ResponseEntity<List<PostListResponse>> getPostsByBoard(@PathVariable Long boardId) {
        List<Post> posts = postService.getPostsByBoard(boardId);
        List<PostListResponse> response = posts.stream()
                .map(post -> new PostListResponse(post.getId(), post.getTitle(), post.getCommentCount()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable Long postId) {
        Post post = postService.getPost(postId);
        PostDetailResponse response = new PostDetailResponse(post.getId(), post.getTitle(), post.getContent(), post.getBoardId(), post.getComments());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> updatePost(@PathVariable Long postId, @Valid @RequestBody PostRequest postRequest) {
        Post updatedPost = postService.updatePost(postId, postRequest);
        PostResponse response = new PostResponse(updatedPost.getId(), updatedPost.getTitle(), updatedPost.getContent(), updatedPost.getBoardId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
