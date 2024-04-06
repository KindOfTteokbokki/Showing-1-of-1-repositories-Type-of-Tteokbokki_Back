package halfandhalf.utteokMain.review.controller;

import halfandhalf.com.config.ResponseMessage;
import halfandhalf.utteokMain.review.service.ReviewService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /*
     *  나도 추천할래 하나 가져오기
     */
    @GetMapping(value="/oneFromReview/{seq}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ViewToOne(@PathVariable("seq") Long seq) {
        try {
            return ResponseEntity.ok(reviewService.findById(seq));
        }
        catch (NotFoundException e) {
            // 해당 id 값의 데이터가 없을 경우
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
