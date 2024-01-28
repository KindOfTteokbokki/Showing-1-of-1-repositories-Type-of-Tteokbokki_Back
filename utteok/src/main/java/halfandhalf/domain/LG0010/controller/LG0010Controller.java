package halfandhalf.domain.LG0010.controller;

import halfandhalf.com.util.jwt.JwtUtils;
import halfandhalf.domain.LG0010.dto.LG0011Dto;
import halfandhalf.domain.LG0010.service.LG0010Service;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/login")
public class LG0010Controller {

    @Autowired
    LG0010Service loginService;

    // JWT 토큰 생성을 위해 필요
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody LG0011Dto memberDTO) {
        try {
            loginService.saveFromLogin(memberDTO);
//            저장
            return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        }
        catch (LoginException e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody LG0011Dto memberDTO){
        try {
            // member_id, password 체크
            LG0011Dto member = loginService.checkLoin(memberDTO.getMember_id(), memberDTO.getMember_password());
            // JWT 토큰 생성 및 반환
            String jwt = jwtUtils.createAccessToken(member.getMember_id(), member.getMember_name());
            // 생성된 JWT 토큰을 응답 본문에 담아 반환
            return ResponseEntity.ok(new JwtResponse(jwt));
        }
        catch (LoginException e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
        catch (AuthenticationException e){
            // 인증 실패한 경우 에러 메세지 + 401 상태 코드 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증 실패 : 아이디나 비밀번호 확인해주세요");
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    // JWT 토큰을 담을 내부 클래스를 정의
    @Getter
    @Setter
    class JwtResponse {
        private String token;

        // 생성자를 통해 토큰을 초기화
        public JwtResponse(String token) {
            this.token = token;
        }
    }

}
