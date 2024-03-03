package halfandhalf.domain.TT0010.controller;


import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.com.annotation.LoginCheckNoEssential;
import halfandhalf.com.config.ResponseMessage;
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

import static halfandhalf.com.aop.LoginCheckAspect.userId;


@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class TT0010Controller {

    private final TT0010Service tT0010Service;

    public TT0010Controller(TT0010Service tT0010Service) {
        this.tT0010Service = tT0010Service;
    }

    /*
     *  질문 및 답 가져오기
     */
    @LoginCheckNoEssential
    @PostMapping(value="/findTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findTitle(@RequestBody TT0010Dto st0010Dto, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(tT0010Service.findTitle(st0010Dto, userId));
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
            List<TT0010Dto> haveTitle = tT0010Service.findHaveTitle(userId);
            TT0012Dto countTitle = tT0010Service.findCountTitle(userId);

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
            return ResponseEntity.ok(tT0010Service.findAllTitleNotHave(userId));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

}
