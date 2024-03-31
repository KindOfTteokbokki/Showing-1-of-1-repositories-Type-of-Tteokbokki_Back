package halfandhalf.utteokMain.combination.controller;

import halfandhalf.com.config.ResponseMessage;
import halfandhalf.utteokMain.combination.entity.CombinationEntity;
import halfandhalf.utteokMain.combination.service.CombinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class CombinationController {

    private final CombinationService combinationService;

    public CombinationController(CombinationService combinationService) {
        this.combinationService = combinationService;
    }

    /*
     *  꿀 조합 하나만 가져오기
     */
    @GetMapping(value="/OneFromCombination", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ViewOneFromCombination1(@RequestParam("combination_seq") Long combination_seq) {
        try {
            return ResponseEntity.ok(combinationService.findOneCombination(combination_seq));
        } catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  꿀 조합 가져오기
     */
    @GetMapping(value="/combinationFour", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> combination1() {
        try {
            return ResponseEntity.ok(new Result(combinationService.findCombination()));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    public static class Result<T> {
        private T data;

        public T getData() {
            return data;
        }

        public Result(T data) {
            this.data = data;
        }
    }
}
