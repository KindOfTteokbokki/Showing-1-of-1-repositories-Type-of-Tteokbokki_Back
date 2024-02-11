package halfandhalf.domain.LD0010.controller;


import halfandhalf.domain.LD0010.dto.LD0010Dto;
import halfandhalf.domain.LD0010.service.LD0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://www.utteok.com"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class LD0010Controller {

    private final LD0010Service lD0010Service;

    @Autowired
    public LD0010Controller(LD0010Service lD0010Service) {
        this.lD0010Service = lD0010Service;
    }

    /*
     *  질문 및 답 가져오기
     */
    @GetMapping(value="/loading", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loading() {
        try {
            List<LD0010Dto> ld0010Dto = lD0010Service.findLoading();

            return ResponseEntity.ok(ld0010Dto);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

}
