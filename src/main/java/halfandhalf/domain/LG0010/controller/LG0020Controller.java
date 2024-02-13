package halfandhalf.domain.LG0010.controller;

import halfandhalf.domain.LG0010.dto.LG0020Dto;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokens;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import halfandhalf.domain.LG0010.oauth.param.KakaoLoginParams;
import halfandhalf.domain.LG0010.oauth.param.NaverLoginParams;
import halfandhalf.domain.LG0010.serviceImpl.LG0020ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:3000", "http://www.utteok.com"}, allowCredentials = "true")
public class LG0020Controller {
    private final LG0020ServiceImpl oAuthLoginService;

    @GetMapping("/tokenForKakao")
    public String loginKakao(@RequestParam(value = "code", required = false) String code) {
        return code;
    }

    @PostMapping("/kakao")
    public ResponseEntity<AuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
//        토큰으로 회원 정보 얻는 두가지 방법
//        AuthTokens getToken = oAuthLoginService.login(params);
//        Long getId = (new AuthTokensGenerator(new JwtTokenProvider(secretKey))).extractMemberId(getToken.getAccessToken());
//        LG0020Dto user = oAuthLoginService.findById(getId);

//        JwtTokenProvider tokenProvider = new JwtTokenProvider(secretKey);
//        AuthTokensGenerator authToken = new AuthTokensGenerator(tokenProvider);
//        Long getId = authToken.extractMemberId(getToken.getAccessToken());

        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @PostMapping("/naver")
    public ResponseEntity<AuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}