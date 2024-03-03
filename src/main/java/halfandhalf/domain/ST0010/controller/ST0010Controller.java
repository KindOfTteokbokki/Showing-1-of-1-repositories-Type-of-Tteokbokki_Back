package halfandhalf.domain.ST0010.controller;


import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.com.annotation.LoginCheckNoEssential;
import halfandhalf.com.config.ResponseMessage;
import halfandhalf.domain.ST0010.dto.ST0010Dto;
import halfandhalf.domain.ST0010.service.ST0010Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static halfandhalf.com.aop.LoginCheckAspect.userId;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ST0010Controller {

    private final ST0010Service sT0010Service;

    public ST0010Controller(ST0010Service sT0010Service) {
        this.sT0010Service = sT0010Service;
    }

    /*
     *  내입맛 데이터 하나 가져오기
     */
    @PostMapping(value="/viewOneFromMyTaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> viewOneFromMyTaste(@RequestBody ST0010Dto st0010Dto) {
        try {
            return ResponseEntity.ok(sT0010Service.findOneFromMyTaste(st0010Dto));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  가게 정보 가져오기
     */
    @LoginCheckNoEssential
    @PostMapping(value="/findStore", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findStore(@RequestBody ST0010Dto st0010Dto, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(sT0010Service.findStore(st0010Dto, userId));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  내 입맛 가져오기
     */
    @LoginCheckEssential
    @GetMapping(value="/myTaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> myTaste(HttpServletRequest request) {
        try {
            return ResponseEntity.ok(sT0010Service.findMyTasteByCount(userId));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }
}
