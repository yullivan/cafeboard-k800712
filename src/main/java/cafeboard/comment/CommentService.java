package cafeboard.comment;

import cafeboard.post.Post;
import cafeboard.post.PostRepository;
import cafeboard.ResourceNotFoundException;
import cafeboard.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.access.AccessDeniedException;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public Comment createComment(Long postId, Comment comment) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("게시글을 찾을 수 없습니다. ID: " + postId));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new AccessDeniedException("인증되지 않은 사용자입니다.");
        }
        Member currentUser = (Member) auth.getPrincipal();

        comment.setPost(post);
        comment.setAuthor(currentUser);

        if (comment.getContent() == null || comment.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용은 비어있을 수 없습니다.");
        }
        if (comment.getContent().length() > 1000) {
            throw new IllegalArgumentException("댓글 내용은 1000자를 초과할 수 없습니다.");
        }

        return commentRepository.save(comment);
    }

    @Transactional
    public Comment updateComment(Long id, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("댓글을 찾을 수 없습니다. ID: " + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || !comment.getAuthor().equals(auth.getPrincipal())) {
            throw new AccessDeniedException("이 댓글에 대한 수정 권한이 없습니다.");
        }

        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용은 비어있을 수 없습니다.");
        }
        if (request.getContent().length() > 1000) {
            throw new IllegalArgumentException("댓글 내용은 1000자를 초과할 수 없습니다.");
        }

        comment.setContent(request.getContent());
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("댓글을 찾을 수 없습니다. ID: " + id));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || !comment.getAuthor().equals(auth.getPrincipal())) {
            throw new AccessDeniedException("이 댓글에 대한 삭제 권한이 없습니다.");
        }

        commentRepository.delete(comment);
    }

    public Page<Comment> getCommentsByPost(Long postId, Pageable pageable) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("게시글을 찾을 수 없습니다. ID: " + postId);
        }
        return commentRepository.findByPostId(postId, pageable);
    }
}
