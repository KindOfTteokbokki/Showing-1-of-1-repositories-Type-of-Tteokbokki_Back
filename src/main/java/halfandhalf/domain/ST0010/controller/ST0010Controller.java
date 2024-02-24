package halfandhalf.domain.ST0010.controller;


import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import halfandhalf.domain.ST0010.dto.ST0010Dto;
import halfandhalf.domain.ST0010.service.ST0010Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ST0010Controller {

    private final ST0010Service sT0010Service;
    private final JwtTokenProvider jwtProvider;
    private final AuthTokensGenerator authTokensGenerator;

    /*
     *  내입맛 데이터 하나 가져오기
     */
    @PostMapping(value="/viewOneFromMyTaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewOneFromMyTaste(@RequestBody ST0010Dto st0010Dto) {
        try {
            ST0010Dto store_info = sT0010Service.findOneFromMyTaste(st0010Dto);
            return ResponseEntity.ok(store_info);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    /*
     *  가게 정보 가져오기
     */
    @PostMapping(value="/findStore", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findStore(@RequestBody ST0010Dto st0010Dto, HttpServletRequest request) {
        try {
            String accessToken = jwtProvider.getAccessToken(request);
            Long user_id = 0L;
            if(StringUtils.hasText(accessToken)) {
                user_id = authTokensGenerator.extractMemberId(accessToken);
            }
            ST0010Dto store_info = sT0010Service.findStore(st0010Dto, user_id);
            return ResponseEntity.ok(store_info);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    /*
     *  내 입맛 가져오기
     */
    @GetMapping(value="/myTaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> myTaste(HttpServletRequest request) {
        try {
            String accessToken = jwtProvider.getAccessToken(request);
            Long user_id = authTokensGenerator.extractMemberId(accessToken);
            List<ST0010Dto> myTaste = sT0010Service.findMyTasteByCount(user_id);
            return ResponseEntity.ok(myTaste);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }
}
