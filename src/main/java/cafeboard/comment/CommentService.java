package cafeboard.comment;

import cafeboard.ResourceNotFoundException;
import cafeboard.post.Post;
import cafeboard.post.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public CommentDto createComment(Long postId, String content) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        Comment comment = new Comment(content, post, null);
        Comment savedComment = commentRepository.save(comment);
        return savedComment.toDto();
    }

    @Transactional
    public CommentDto updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
        comment.setContent(content);
        Comment updatedComment = commentRepository.save(comment);
        return updatedComment.toDto();
    }

    @Transactional
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment not found with id: " + commentId);
        }
        commentRepository.deleteById(commentId);
    }

    public List<CommentDto> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(Comment::toDto)
                .collect(Collectors.toList());
    }
}