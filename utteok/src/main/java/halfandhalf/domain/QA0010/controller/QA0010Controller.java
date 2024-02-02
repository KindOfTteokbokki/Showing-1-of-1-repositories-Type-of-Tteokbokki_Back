package halfandhalf.domain.QA0010.controller;


import halfandhalf.domain.QA0010.dto.QA0010Dto;
import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;
import halfandhalf.domain.QA0010.service.QA0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://www.utteok.com"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class QA0010Controller {

    private final QA0010Service qA0010Service;

    @Autowired
    public QA0010Controller(QA0010Service qA0010Service) {
        this.qA0010Service = qA0010Service;
    }

    /*
     *  질문 및 답 가져오기
     */
//    //@CrossOrigin(origins = {"118.67.132.171:3000"},allowCredentials = "true")
//    @RequestMapping(value = "/getQA", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    @GetMapping(value="/getQA", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTodos1() {
        try {
            List<QA0011Dto> qA0011Dto = qA0010Service.findQuestion();
            List<QA0012Dto> qA0012Dto = qA0010Service.findAnswer();

            QA0010Dto qA0010 = new QA0010Dto(qA0011Dto, qA0012Dto);

            return ResponseEntity.ok(qA0010);
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류");
        }
    }

}