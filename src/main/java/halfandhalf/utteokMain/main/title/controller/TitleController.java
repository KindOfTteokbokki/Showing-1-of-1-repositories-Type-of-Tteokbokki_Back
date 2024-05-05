package halfandhalf.utteokMain.main.title.controller;

import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.com.config.ResponseMessage;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator_;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider_;
import halfandhalf.domain.TT0010.dto.TT0010Dto;
import halfandhalf.domain.TT0010.dto.TT0012Dto;
import halfandhalf.utteokMain.main.title.dto.TitleDto;
import halfandhalf.utteokMain.main.title.service.TitleService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class TitleController {

    private final TitleService titleService;
    private final AuthTokensGenerator_ authTokensGenerator;
    private final JwtTokenProvider_ jwtProvider;

    public TitleController(TitleService titleService, AuthTokensGenerator_ authTokensGenerator, JwtTokenProvider_ jwtProvider) {
        this.titleService = titleService;
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
    }

    /*
     *  질문 및 답 가져오기
     */
    @PostMapping(value="/findTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findTitle(@RequestBody TitleDto TitleDto, HttpServletRequest request) {
        return ResponseEntity.ok(titleService.findTitle(
                TitleDto,
                authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)) != null ? authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)) : 0
        ));
    }

    /*
     *  가지고 있는 칭호 가져오기
     */
    @LoginCheckEssential
    @GetMapping(value = "/haveTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> haveTitle (HttpServletRequest request) {
        List<TT0010Dto> haveTitle = titleService.findHaveTitle(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));
        TT0012Dto countTitle = titleService.findCountTitle(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("haveTitle", haveTitle);
        responseData.put("countTitle", countTitle);

        return ResponseEntity.ok(responseData);
    }

    /*
     *  없는 칭호
     */
    @LoginCheckEssential
    @GetMapping(value = "/doNotHaveTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> doNotHaveTitle (HttpServletRequest request) {
        return ResponseEntity.ok(titleService.findAllTitleNotHave(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request))));
    }

    @Getter
    public static class Result {
        private final Collection<?> data;

        public Result(Collection<?> data) {
            this.data = data;
        }
    }
}
