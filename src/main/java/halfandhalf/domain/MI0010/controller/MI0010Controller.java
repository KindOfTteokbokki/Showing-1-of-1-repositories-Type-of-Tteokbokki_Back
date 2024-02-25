package halfandhalf.domain.MI0010.controller;


import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import halfandhalf.domain.MI0010.service.MI0010Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MI0010Controller {

    private final MI0010Service mI0010Service;
    private final JwtTokenProvider jwtProvider;
    private final AuthTokensGenerator authTokensGenerator;

    /*
     *  내 정보 불러오기
     */
    @GetMapping(value="/myInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loading(HttpServletRequest request) {
        try {
            String accessToken = jwtProvider.getAccessToken(request);
            Long userId = authTokensGenerator.extractMemberId(accessToken);

            String myInfo = mI0010Service.findMyInfo(userId);

            return ResponseEntity.ok(myInfo);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

}
