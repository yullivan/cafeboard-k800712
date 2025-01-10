//package cafeboard.member;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class MemberApiTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private MemberService memberService;
//
//    @InjectMocks
//    private MemberController memberController;
//
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    public void testSignup() throws Exception {
//        // Given
//        Member member = new Member();
//        member.setUsername("testuser");
//        member.setPassword("password123");
//        member.setEmail("test@example.com");
//
//        when(memberService.signup(any(Member.class))).thenReturn(member);
//
//        // When & Then
//        mockMvc.perform(post("/api/members/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(member)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void testDeleteMember() throws Exception {
//        // Given
//        Long memberId = 1L;
//
//        // When & Then
//        mockMvc.perform(delete("/api/members/{memberId}", memberId))
//                .andExpect(status().isNoContent());
//    }
//}
