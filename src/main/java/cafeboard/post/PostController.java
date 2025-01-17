package cafeboard.post;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/boards/{boardId}/posts")
    public ResponseEntity<List<PostListResponse>> getPostsByBoard(@PathVariable Long boardId) {
        try {
            log.info("Fetching posts for board ID: {}", boardId);
            List<Post> posts = postService.getPostsByBoard(boardId);
            log.info("Found {} posts", posts.size());
            
            List<PostListResponse> response = posts.stream()
                .map(post -> new PostListResponse(
                    post.getId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getCommentCount()
                ))
                .collect(Collectors.toList());
                
            log.info("Returning response: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching posts for board ID: {}", boardId, e);
            throw e;
        }
    }

    @PostMapping("/boards/{boardId}/posts")
    public ResponseEntity<PostResponse> createPost(
            @PathVariable Long boardId,
            @Valid @RequestBody PostRequest postRequest) {
        try {
            log.debug("Creating post for board ID: {}", boardId);
            Post createdPost = postService.createPost(boardId, postRequest);
            PostResponse response = PostResponse.from(createdPost);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            log.error("Error creating post for board ID: {}", boardId, e);
            throw e;
        }
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailResponse> getPost(@PathVariable Long postId) {
        try {
            log.debug("Fetching post details for ID: {}", postId);
            Post post = postService.getPost(postId);
            PostDetailResponse response = PostDetailResponse.from(post);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching post details for ID: {}", postId, e);
            throw e;
        }
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long postId,
            @Valid @RequestBody PostRequest postRequest) {
        try {
            log.debug("Updating post ID: {}", postId);
            Post updatedPost = postService.updatePost(postId, postRequest);
            PostResponse response = PostResponse.from(updatedPost);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error updating post ID: {}", postId, e);
            throw e;
        }
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        try {
            log.debug("Deleting post ID: {}", postId);
            postService.deletePost(postId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting post ID: {}", postId, e);
            throw e;
        }
    }
}