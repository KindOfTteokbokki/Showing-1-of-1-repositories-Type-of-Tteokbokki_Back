package halfandhalf.domain.MI0010.controller;


import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.com.config.ResponseMessage;
import halfandhalf.domain.MI0010.service.MI0010Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static halfandhalf.com.aop.LoginCheckAspect.userId;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class MI0010Controller {

    private final MI0010Service mI0010Service;

    public MI0010Controller(MI0010Service mI0010Service) {
        this.mI0010Service = mI0010Service;
    }

    /*
     *  내 정보 불러오기
     */
    @LoginCheckEssential
    @GetMapping(value="/myInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> myInfo(HttpServletRequest request) {
        try {
            return ResponseEntity.ok(mI0010Service.findMyInfo(userId));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

}
