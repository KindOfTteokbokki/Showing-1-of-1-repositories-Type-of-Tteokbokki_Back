package halfandhalf.utteokMain.review.controller;

import halfandhalf.com.annotation.LoginCheckEssential;
import halfandhalf.com.config.ResponseMessage;
import halfandhalf.com.exception.FileUploadException;
import halfandhalf.com.exception.ForbiddenException;
import halfandhalf.com.exception.ValidationException;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator_;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider_;
import halfandhalf.utteokMain.review.dto.ReviewDto;
import halfandhalf.utteokMain.review.service.ReviewService;
import lombok.Getter;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

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
    public ResponseEntity<?> ViewToOne(@PathVariable("seq") Long seq) throws NotFoundException {
        return ResponseEntity.ok(reviewService.findById(seq));
    }

    /*
     *  나도 추천할래 4개
     */
    @GetMapping("/fourFromReview")
    public ResponseEntity<?> fourFromReview() {
        return ResponseEntity.ok(new Result(reviewService.findTop4ByOrderByIdDesc()));
    }

    /*
     *  나도 추천할래 페이징
     */
    @GetMapping("/reviewPage")
    public ResponseEntity<?> reviewPage(@PageableDefault(size = 15) Pageable pageable) {
        return ResponseEntity.ok(new Result(reviewService.findSliceByOrderByIdDesc(pageable)));
    }

    /*
     *  나도 추천할래
     */
    @LoginCheckEssential
    @PostMapping("/reviewPageMyinfo")
    public ResponseEntity<?> getRecommendToPageInMyinfo(Pageable pageable, HttpServletRequest request) throws NotFoundException {
        return ResponseEntity.ok(reviewService.findById(pageable, authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request))));
    }

    /*
     *  나도 추천할래 등록하기
     */
    @LoginCheckEssential
    @PostMapping(value="/saveReview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveRecommend(@RequestParam String content,
                                           @RequestPart(value = "file", required=false) MultipartFile file,
                                           HttpServletRequest request) throws FileUploadException {
        reviewService.saveReview(file, content, authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)));
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /*
     *  나도 추천할래 수정하기
     */
    @LoginCheckEssential
    @PatchMapping(value="/modifyReview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyRecommend(@RequestBody ReviewDto dto,
                                             @RequestPart(value = "file", required=false) MultipartFile file,
                                             HttpServletRequest request) throws Exception {

        if(!dto.getUser_id().equals(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request))))
            throw new ForbiddenException("콘텐츠에 접근할 권리를 가지고 있지 않습니다.");

        reviewService.modifyReview(dto, file);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    /*
     *  나도 추천할래 삭제하기
     */
    @LoginCheckEssential
    @PostMapping(value="/deleteReview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteRecommend(
            @RequestParam Long user_id, @RequestParam Long review_seq, HttpServletRequest request) throws ForbiddenException {

        if (!user_id.equals(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request))))
            throw new ForbiddenException("콘텐츠에 접근할 권리를 가지고 있지 않습니다.");

        reviewService.deleteReview(review_seq);
        return ResponseEntity.ok(ResponseMessage.valueOfCode("Ok").getMessage());
    }

    @Getter
    public static class Result<T> {
        private final T data;

        public Result(T data) {
            this.data = data;
        }
    }
}
