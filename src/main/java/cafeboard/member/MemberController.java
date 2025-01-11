package cafeboard.member;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Member> signup(@Valid @RequestBody SignupRequest signupRequest) {
        Member member = new Member();
        member.setUsername(signupRequest.getUsername());
        member.setPassword(signupRequest.getPassword());
        member.setEmail(signupRequest.getEmail());

        Member savedMember = memberService.signup(member);
        savedMember.setPassword(null);
        return ResponseEntity.ok(savedMember);
    }


    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Member member = memberService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(member);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

}
