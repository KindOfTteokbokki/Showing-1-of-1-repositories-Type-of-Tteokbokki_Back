package halfandhalf.utteokMain.questionAndAnswer.controller;


import halfandhalf.com.config.ResponseMessage;
import halfandhalf.utteokMain.questionAndAnswer.dto.QaDto;
import halfandhalf.utteokMain.questionAndAnswer.entity.QaEntity;
import halfandhalf.utteokMain.questionAndAnswer.repository.QaRepository;
import halfandhalf.utteokMain.questionAndAnswer.service.QaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class QaController {

    private final QaService qaService;

    @Autowired
    QaRepository qaRepository;
    public QaController(QaService qaService) {
        this.qaService = qaService;
    }

    /*
     *  로딩 페이지
     */
    @GetMapping(value="/questionAndAnswer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> questionAndAnswer() {
        long start = System.currentTimeMillis();
        List<QaDto> y = qaService.findByCodeIdAndUseYn("101", "Y");
        List<QaDto> y1 = qaService.findByCodeIdAndUseYn("102", "Y");
        long end = System.currentTimeMillis();
        Result result = new Result(
                y,y1, end-start
        );

        return ResponseEntity.ok(result
        );
    }

    public static class Result<T> {
        private T question;
        private T answer;
        private T time;

        public T getQuestion() {
            return question;
        }
        public T getAnswer() {
            return answer;
        }
        public T time() {return time;}

        public Result(T question, T answer, T time) {
            this.question = question;
            this.answer = answer;
            this.time = time;
        }
    }
}
