package halfandhalf.utteokMain.member.controller;

import halfandhalf.utteokMain.member.oauth.jwt.AuthTokens;
import halfandhalf.utteokMain.member.oauth.param.KakaoLoginParams;
import halfandhalf.utteokMain.member.oauth.param.NaverLoginParams;
import halfandhalf.utteokMain.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService oAuthLoginService;

    public MemberController(MemberService oAuthLoginService) {
        this.oAuthLoginService = oAuthLoginService;
    }

    @GetMapping("/getTokenToKakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestParam(value = "code", required = false) String code) {
        KakaoLoginParams params = new KakaoLoginParams(code);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @GetMapping("/getTokenToNaver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestParam(value = "code", required = false) String code) {
        NaverLoginParams params = new NaverLoginParams(code);
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

}
