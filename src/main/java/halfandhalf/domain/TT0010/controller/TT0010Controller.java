package halfandhalf.domain.TT0010.controller;


import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import halfandhalf.domain.TT0010.dto.TT0010Dto;
import halfandhalf.domain.TT0010.dto.TT0012Dto;
import halfandhalf.domain.TT0010.service.TT0010Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TT0010Controller {

    private final TT0010Service tT0010Service;
    private final JwtTokenProvider jwtProvider;
    private final AuthTokensGenerator authTokensGenerator;

    /*
     *  질문 및 답 가져오기
     */
    @PostMapping(value="/findTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findTitle(@RequestBody TT0010Dto st0010Dto, HttpServletRequest request) {
        try {
            String accessToken = jwtProvider.getAccessToken(request);
            Long user_id = 0L;
            if(StringUtils.hasText(accessToken)) {
                user_id = authTokensGenerator.extractMemberId(accessToken);
            }
            TT0010Dto title_info = tT0010Service.findTitle(st0010Dto, user_id);
            return ResponseEntity.ok(title_info);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    /*
     *  가지고 있는 칭호 가져오기
     */
    @GetMapping(value = "/haveTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> haveTitle (HttpServletRequest request) {
        try {
            String accessToken = jwtProvider.getAccessToken(request);
            Long user_id = authTokensGenerator.extractMemberId(accessToken);

            List<TT0010Dto> haveTitle = tT0010Service.findHaveTitle(user_id);
            TT0012Dto countTitle = tT0010Service.findCountTitle(user_id);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("haveTitle", haveTitle);
            responseData.put("countTitle", countTitle);

            return ResponseEntity.ok(responseData);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    /*
     *  없는 칭호
     */
    @GetMapping(value = "/doNotHaveTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doNotHaveTitle (HttpServletRequest request) {
        try {
            String accessToken = jwtProvider.getAccessToken(request);
            Long userId = authTokensGenerator.extractMemberId(accessToken);
            List<TT0012Dto> haveTitle = tT0010Service.findAllTitleNotHave(userId);
            return ResponseEntity.ok(haveTitle);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

}
