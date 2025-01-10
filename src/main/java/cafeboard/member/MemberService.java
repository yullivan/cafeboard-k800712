package cafeboard.member;

import cafeboard.ResourceNotFoundException;
import cafeboard.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member signup(Member member) {
        if (memberRepository.existsByUsername(member.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일 주소입니다.");
        }

        member.setPassword(SecurityUtils.sha256Encrypt(member.getPassword()));
        return memberRepository.save(member);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));

        // 회원과 관련된 게시글, 댓글의 작성자 정보를 null로 설정
        member.getPosts().forEach(post -> post.setAuthor(null));
        member.getComments().forEach(comment -> comment.setAuthor(null));

        memberRepository.delete(member);
    }
}
