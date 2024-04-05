package halfandhalf.utteokMain.questionAndAnswer.controller;


import halfandhalf.com.config.ResponseMessage;
import halfandhalf.utteokMain.questionAndAnswer.entity.QaEntity;
import halfandhalf.utteokMain.questionAndAnswer.service.QaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class QaController {

    private final QaService qaService;

    public QaController(QaService qaService) {
        this.qaService = qaService;
    }

    /*
     *  로딩 페이지
     */
    @GetMapping(value="/questionAndAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> questionAndAnswer() {
        try {
            return ResponseEntity.ok(
                    new Result(
                              qaService.findByCodeIdAndUseYn("101", "Y")
                            , qaService.findByCodeIdAndUseYn("102", "Y")
                    )
            );
        }
        catch(Exception e){
            System.out.println("e = " + e.getMessage());
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    public static class Result<T> {
        private T question;
        private T answer;

        public T getQuestion() {
            return question;
        }
        public T getAnswer() {
            return answer;
        }

        public Result(T question, T answer) {
            this.question = question;
            this.answer = answer;
        }
    }
}
