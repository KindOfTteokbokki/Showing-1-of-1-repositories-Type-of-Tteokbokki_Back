package halfandhalf.domain.ST0010.controller;


import halfandhalf.domain.ST0010.dto.ST0010Dto;
import halfandhalf.domain.ST0010.service.ST0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://www.utteok.com"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ST0010Controller {

    private final ST0010Service sT0010Service;

    @Autowired
    public ST0010Controller(ST0010Service sT0010Service) {
        this.sT0010Service = sT0010Service;
    }

    /*
     *  가게 정보 가져오기
     */
    @PostMapping(value="/findStore", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStoreInfo(@RequestBody ST0010Dto st0010Dto) {
        try {
            ST0010Dto store_info = sT0010Service.findStore(st0010Dto);
            return ResponseEntity.ok(store_info);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

    /*
     *  내 입맛 가져오기
     */
    @GetMapping(value="/myTaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTodos1() {
        try {
            List<ST0010Dto> myTaste = sT0010Service.findByCount();
            return ResponseEntity.ok(myTaste);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }
}
