package halfandhalf.domain.LG0010.controller;

import halfandhalf.domain.LG0010.oauth.jwt.AuthTokens;
import halfandhalf.domain.LG0010.oauth.param.KakaoLoginParams;
import halfandhalf.domain.LG0010.oauth.param.NaverLoginParams;
import halfandhalf.domain.LG0010.serviceImpl.LG0020ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://118.67.132.171"}, allowCredentials = "true")
public class LG0020Controller {
    private final LG0020ServiceImpl oAuthLoginService;

    @GetMapping("/tokenForKakao")
    public String tokenForKakao(@RequestParam(value = "code", required = false) String code) {
        return code;
    }

    @GetMapping("/tokenForNaver")
    public String tokenForNaver(@RequestParam(value = "code", required = false) String code) {
        return code;
    }

    @GetMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestParam(value = "code", required = false) String code) {
        KakaoLoginParams params = new KakaoLoginParams(code);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @GetMapping("/naver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestParam(value = "code", required = false) String code) {
        NaverLoginParams params = new NaverLoginParams(code);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

//    @PostMapping("/naver")
//    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
//        return ResponseEntity.ok(oAuthLoginService.login(params));
//    }
}