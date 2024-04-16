package halfandhalf.utteokMain.review.service;

import halfandhalf.com.config.ResponseMessage;
import halfandhalf.com.exception.FileUploadException;
import halfandhalf.utteokMain.review.dto.ReviewDto;
import halfandhalf.utteokMain.review.entity.ReviewEntity;
import halfandhalf.utteokMain.review.repository.ReviewRepository;
import halfandhalf.utteokMain.review.upload.UploadData;
import halfandhalf.utteokMain.review.upload.UploadImg;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ReviewService {

    @Value("${api.upload.dir.review}")
    private static String uploadDir;
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewDto findById(Long seq) throws NotFoundException {
        Optional<ReviewEntity> findOne = reviewRepository.findById(seq);

        if(findOne.isPresent()) {
            return new ReviewDto(findOne.get());
        } else {
            throw new NotFoundException(ResponseMessage.valueOfCode("NotFound").getMessage());
        }
    }

    public List<ReviewDto> findById(Pageable pageable, Long id) throws NotFoundException {
        Page<ReviewEntity> findMyReview = reviewRepository.findById(pageable, id);
        if(findMyReview.hasNext()) {
            return findMyReview.map(ReviewDto::new).getContent();
        } else {
            throw new NotFoundException(ResponseMessage.valueOfCode("NotFound").getMessage());
        }
    }

    public List<ReviewDto> findTop4ByOrderByIdDesc() {
        return transDto(reviewRepository.findTop4ByOrderByIdDesc());
    }

    public List<ReviewDto> findSliceByOrderByIdDesc(Pageable pageable) {
        return transDto(reviewRepository.findSliceByOrderByIdDesc(pageable).getContent());
    }

    @Transactional
    public void saveReview(MultipartFile file, String content, Long id) throws FileUploadException {
        reviewRepository.save(new ReviewEntity(upload(file, content, id)));
    }

    @Transactional
    public void modifyReview(ReviewDto dto, MultipartFile file) throws Exception {
        reviewRepository.modifyRecommend(upload(rv0010Dto, file));
    }

    // Dto to Entity
    private List<ReviewDto> transDto(List<ReviewEntity> entityList) {
        return entityList.stream().map(ReviewDto::new).collect(Collectors.toList());
    }

    private ReviewDto upload(MultipartFile file, String content, Long id) throws FileUploadException {
        String path; String original; String masking;

        try{
            UploadData uploadData = new UploadData(ReviewService.uploadDir, file);
            UploadImg.uploadOperating(uploadData);

            path = uploadData.getDirPath();
            original = uploadData.getOriginal();
            masking = uploadData.getMasking();
        } catch (FileUploadException e){
            log.error("Exception [ReviewDto upload] : {}", e.getStackTrace()[0]);
            throw new FileUploadException("파일 누락, 또는 다른 형식으로 요청하였습니다.");
        }

        return new ReviewDto(id, content, path, original, masking);
    }
}
