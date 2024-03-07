package halfandhalf.domain.LD0010.controller;


import halfandhalf.com.config.ResponseMessage;
import halfandhalf.domain.LD0010.service.LD0010Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class LD0010Controller {

    private final LD0010Service lD0010Service;

    public LD0010Controller(LD0010Service lD0010Service) {
        this.lD0010Service = lD0010Service;
    }

    /*
     *  로딩 페이지
     */
    @GetMapping(value="/loading", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loading() {
        try {
            return ResponseEntity.ok(lD0010Service.findLoading());
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

}
