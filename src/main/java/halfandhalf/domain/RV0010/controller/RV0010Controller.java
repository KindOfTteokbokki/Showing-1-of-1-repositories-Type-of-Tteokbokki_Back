package halfandhalf.domain.RV0010.controller;


import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.com.config.ResponseMessage;
import halfandhalf.com.exception.FileUploadException;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator_;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider_;
import halfandhalf.domain.RV0010.dto.RV0010Dto;
import halfandhalf.domain.RV0010.dto.RV0011Dto;
import halfandhalf.domain.RV0010.service.RV0010Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class RV0010Controller {

    private final RV0010Service rV0010Service;
    private final AuthTokensGenerator_ authTokensGenerator;
    private final JwtTokenProvider_ jwtProvider;

    public RV0010Controller(RV0010Service rV0010Service, AuthTokensGenerator_ authTokensGenerator, JwtTokenProvider_ jwtProvider) {
        this.rV0010Service = rV0010Service;
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
    }

    /*
     *  나도 추천할래 하나 가져오기
     */
    @PostMapping(value="/ViewOneFromRecommend", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ViewToOne(@RequestBody RV0010Dto rv0010Dto) {
        try {
            return ResponseEntity.ok(rV0010Service.findOneFromRecommend(rv0010Dto));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  나도 추천할래 페이징
     */
    @PostMapping("/getRecommendToPage")
    public ResponseEntity<?> getRecommendToPage(@RequestBody RV0011Dto rv0011Dto, HttpServletRequest request) {
        try {
            final Long[] id = {0L};
            Optional.ofNullable(request.getHeader("Authorization"))
                    .ifPresent(a-> {
                        id[0] = authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request));
                    });
            rv0011Dto.setUser_id(id[0]);
            return ResponseEntity.ok(rV0010Service.findRecommendByPage(rv0011Dto));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  나도 추천할래
     */
    @LoginCheckEssential
    @PostMapping("/getRecommendToPageInMyinfo")
    public ResponseEntity<?> getRecommendToPageInMyinfo(@RequestBody RV0011Dto rv0011Dto, HttpServletRequest request) {
        try {
            rv0011Dto.setUser_id(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));
            return ResponseEntity.ok(rV0010Service.getRecommendToPageInMyinfo(rv0011Dto));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  나도 추천할래 등록하기
     */
    @LoginCheckEssential
    @PostMapping(value="/saveRecommend", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRecommend(@ModelAttribute RV0010Dto rv0010Dto,
                                           @RequestPart(value = "file", required=false) MultipartFile file,
                                           HttpServletRequest request) {
        try {
            rv0010Dto.setUser_id(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));
            rV0010Service.saveRecommend(rv0010Dto, file);
            return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
        } catch (FileUploadException e) {
            // 파일 업로드 실패한 경우 에러 메세지 + 400 상태 코드 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.valueOfCode("InvalidParams").getMessage());
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  나도 추천할래 수정하기
     */
    @LoginCheckEssential
    @PostMapping(value="/modifyRecommend", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyRecommend(@ModelAttribute RV0010Dto rv0010Dto,
                                             @RequestPart(value = "file", required=false) MultipartFile file,
                                             HttpServletRequest request) throws Exception{
        try {
            if(!rv0010Dto.getUser_id().equals(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseMessage.valueOfCode("Forbidden").getMessage());
            }
            else {
                rV0010Service.modifyRecommend(rv0010Dto, file);
                return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
            }
        }
        catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.valueOfCode("InvalidParams").getMessage());
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  나도 추천할래 삭제하기
     */
    @LoginCheckEssential
    @PostMapping(value="/deleteRecommend", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRecommend(@RequestBody RV0010Dto rv0010Dto, HttpServletRequest request){
        try {
            if (rv0010Dto.getUser_id().equals(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)))) {
                rV0010Service.deleteRecommend(rv0010Dto);
                return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ResponseMessage.valueOfCode("Forbidden").getMessage());
            }
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }
}
