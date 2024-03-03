package halfandhalf.domain.CB0010.controller;


import halfandhalf.com.config.ResponseMessage;
import halfandhalf.domain.CB0010.dto.CB0010Dto;
import halfandhalf.domain.CB0010.service.CB0010Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class CB0010Controller {

    private final CB0010Service cB0010Service;

    public CB0010Controller(CB0010Service cB0010Service) {
        this.cB0010Service = cB0010Service;
    }

    /*
     *  꿀 조합 하나만 가져오기
     */
    @PostMapping(value="/ViewOneFromCombination", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ViewOneFromCombination(@RequestBody CB0010Dto cb0010Dto) {
        try {
            return ResponseEntity.ok(cB0010Service.findOneFromCombination(cb0010Dto));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  꿀 조합 가져오기
     */
    @GetMapping(value="/combination", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> combination() {
        try {
            return ResponseEntity.ok(cB0010Service.findCombination());
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }
}
