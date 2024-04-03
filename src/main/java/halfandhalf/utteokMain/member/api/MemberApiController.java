package halfandhalf.utteokMain.member.api;

import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.com.config.ResponseMessage;
import halfandhalf.com.exception.ValidationException;
import halfandhalf.utteokMain.member.dto.MemberDto;
import halfandhalf.utteokMain.member.oauth.jwt.AuthTokensGenerator;
import halfandhalf.utteokMain.member.oauth.jwt.JwtTokenProvider;
import halfandhalf.utteokMain.member.service.MemberApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
public class MemberApiController {
    private final MemberApiService memberApiService;
    private final AuthTokensGenerator authTokensGenerator;
    private final JwtTokenProvider jwtProvider;

    public MemberApiController(MemberApiService memberApiService, AuthTokensGenerator authTokensGenerator, JwtTokenProvider jwtProvider) {
        this.memberApiService = memberApiService;
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
    }

    /*
     * 닉네임 사용 여부 체크 - true 사용중 false 미사용
     */
    @GetMapping("/checkUtteokNickname")
    public ResponseEntity<?> checkNickname(@RequestParam("nickname") String nickname){
        try {
            return ResponseEntity.ok(memberApiService.checkIfEnabledNickName(nickname));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     * 닉네임 등록
     */
    @LoginCheckEssential
    @PatchMapping("/regiUtteokNickname")
    public ResponseEntity<?> regiNickname(@RequestBody MemberDto memberDto, HttpServletRequest request){
        try {
            memberDto.setId(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));
            memberApiService.registNickname(memberDto);

            return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
        }
        catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseMessage.valueOfCode("Validation").getMessage());
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     * 닉네임 존재 여부 확인
     */
    @LoginCheckEssential
    @GetMapping("/isUtteokNickName")
    public ResponseEntity<?> useCheckNickName(HttpServletRequest request){
        try {
            return ResponseEntity.ok(memberApiService.userCheckNickName(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request))));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

}