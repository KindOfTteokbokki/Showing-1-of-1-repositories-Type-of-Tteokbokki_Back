package halfandhalf.domain.TT0010.controller;


import halfandhalf.domain.TT0010.dto.TT0010Dto;
import halfandhalf.domain.TT0010.service.TT0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:3000", "http://www.utteok.com"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class TT0010Controller {

    private final TT0010Service tT0010Service;

    @Autowired
    public TT0010Controller(TT0010Service tT0010Service) {
        this.tT0010Service = tT0010Service;
    }

    /*
     *  질문 및 답 가져오기
     */
    @PostMapping(value="/findTitle", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTodos1(@RequestBody TT0010Dto st0010Dto) {
        try {
            TT0010Dto title_info = tT0010Service.findTitle(st0010Dto);
            return ResponseEntity.ok(title_info);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

}
