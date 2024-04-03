package halfandhalf.domain.TT0010.controller;


import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.com.config.ResponseMessage;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator_;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider_;
import halfandhalf.domain.TT0010.dto.TT0010Dto;
import halfandhalf.domain.TT0010.dto.TT0012Dto;
import halfandhalf.domain.TT0010.service.TT0010Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class TT0010Controller {

    private final TT0010Service tT0010Service;
    private final AuthTokensGenerator_ authTokensGenerator;
    private final JwtTokenProvider_ jwtProvider;

    public TT0010Controller(TT0010Service tT0010Service, AuthTokensGenerator_ authTokensGenerator, JwtTokenProvider_ jwtProvider) {
        this.tT0010Service = tT0010Service;
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
    }

    /*
     *  질문 및 답 가져오기
     */
    @PostMapping(value="/findTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findTitle(@RequestBody TT0010Dto st0010Dto, HttpServletRequest request) {
        try {
            final Long[] id = {0L};
            Optional.ofNullable(request.getHeader("Authorization"))
                    .ifPresent(a-> {
                        id[0] = authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request));
                    });
            return ResponseEntity.ok(tT0010Service.findTitle(st0010Dto, id[0]));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  가지고 있는 칭호 가져오기
     */
    @LoginCheckEssential
    @GetMapping(value = "/haveTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> haveTitle (HttpServletRequest request) {
        try {
            List<TT0010Dto> haveTitle = tT0010Service.findHaveTitle(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));
            TT0012Dto countTitle = tT0010Service.findCountTitle(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("haveTitle", haveTitle);
            responseData.put("countTitle", countTitle);

            return ResponseEntity.ok(responseData);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  없는 칭호
     */
    @LoginCheckEssential
    @GetMapping(value = "/doNotHaveTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doNotHaveTitle (HttpServletRequest request) {
        try {
            return ResponseEntity.ok(tT0010Service.findAllTitleNotHave(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request))));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

}
