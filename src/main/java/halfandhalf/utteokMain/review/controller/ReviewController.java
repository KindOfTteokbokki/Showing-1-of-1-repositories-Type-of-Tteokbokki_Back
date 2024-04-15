package halfandhalf.utteokMain.review.controller;

import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.com.config.ResponseMessage;
import halfandhalf.com.exception.FileUploadException;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator_;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider_;
import halfandhalf.domain.RV0010.dto.RV0010Dto;
import halfandhalf.domain.RV0010.dto.RV0011Dto;
import halfandhalf.utteokMain.review.service.ReviewService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final AuthTokensGenerator_ authTokensGenerator;
    private final JwtTokenProvider_ jwtProvider;

    public ReviewController(ReviewService reviewService, AuthTokensGenerator_ authTokensGenerator, JwtTokenProvider_ jwtProvider) {
        this.reviewService = reviewService;
        this.authTokensGenerator = authTokensGenerator;
        this.jwtProvider = jwtProvider;
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

    /*
     *  나도 추천할래 4개
     */
    @GetMapping("/fourFromReview")
    public ResponseEntity<?> fourFromReview() {
        try {
            return ResponseEntity.ok(new Result(reviewService.findTop4ByOrderByIdDesc()));
        }
        catch(Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  나도 추천할래 페이징
     */
    @GetMapping("/reviewPage")
    public ResponseEntity<?> reviewPage(@PageableDefault(size = 15) Pageable pageable) {
        try {
            return ResponseEntity.ok(new Result(reviewService.findSliceByOrderByIdDesc(pageable)));
        }
        catch(Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  나도 추천할래
     */
    @LoginCheckEssential
    @PostMapping("/reviewPageMyinfo")
    public ResponseEntity<?> getRecommendToPageInMyinfo(Pageable pageable, HttpServletRequest request) {
        try {
            return ResponseEntity.ok(reviewService.findById(pageable, authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request))));
        }
        catch(Exception e){
            // 그 외 에러의 경우 500 메세지
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.valueOfCode("InternalServerError").getMessage());
        }
    }

    /*
     *  나도 추천할래 등록하기
     */
    @LoginCheckEssential
    @PostMapping(value="/saveReview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRecommend(@RequestParam String content,
                                           @RequestPart(value = "file", required=false) MultipartFile file,
                                           HttpServletRequest request) {
        try {
            reviewService.saveReview(file, content, authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));
            return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
        } catch (FileUploadException e) {
            // 파일 업로드 실패한 경우 에러 메세지 + 400 상태 코드 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseMessage.valueOfCode("InvalidParams").getMessage());
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
