package halfandhalf.domain.LG0010.controller;

import halfandhalf.domain.LG0010.dto.LG0020Dto;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokens;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import halfandhalf.domain.LG0010.oauth.param.KakaoLoginParams;
import halfandhalf.domain.LG0010.oauth.param.NaverLoginParams;
import halfandhalf.domain.LG0010.service.LG0030Service;
import halfandhalf.domain.LG0010.serviceImpl.LG0020ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
public class LG0020Controller {

    private final LG0020ServiceImpl oAuthLoginService;

    public LG0020Controller(LG0020ServiceImpl oAuthLoginService) {
        this.oAuthLoginService = oAuthLoginService;
    }

    @GetMapping("/naverForCode")
    public String code(@RequestParam(value="code") String code) {
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
}