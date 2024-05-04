package halfandhalf.utteokMain.main.store.controller;

import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator_;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider_;
import halfandhalf.utteokMain.main.commonDto.QuestionDto;
import halfandhalf.utteokMain.main.store.service.StoreService;
import lombok.Getter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;
    private final AuthTokensGenerator_ authTokensGenerator;
    private final JwtTokenProvider_ jwtProvider;

    public StoreController(StoreService storeService, AuthTokensGenerator_ authTokensGenerator, JwtTokenProvider_ jwtProvider) {
        this.storeService = storeService;
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
    }

    /*
     *  내입맛 데이터 하나 가져오기
     */
    @PostMapping(value="/findOneTaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findOneTaste(@RequestParam Long store_seq) {
        return ResponseEntity.ok(storeService.findOneTaste(store_seq));
    }

    /*
     *  가게 정보 가져오기
     */
    @PostMapping(value="/findTasteInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findTasteInfo(@RequestBody QuestionDto dto, HttpServletRequest request) {
        var ref = new Object() {
            Long id;
        };
        Optional.ofNullable(request.getHeader("Authorization"))
                .ifPresent(a-> {
                    ref.id = authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request));
                });
        return ResponseEntity.ok(storeService.findStoreInfo(dto, ref.id));
    }

    /*
     *  내 입맛 가져오기
     */
    @LoginCheckEssential
    @GetMapping(value="/findMyTaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findMyTaste(HttpServletRequest request) {
        return ResponseEntity.ok(new Result(storeService.findMyTaste(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)))));
    }

    @Getter
    public static class Result {
        private final Collection<?> data;

        public Result(Collection<?> data) {
            this.data = data;
        }
    }
}
