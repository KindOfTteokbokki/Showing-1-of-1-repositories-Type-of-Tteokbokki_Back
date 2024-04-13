package halfandhalf.utteokMain.review.service;

import halfandhalf.com.config.ResponseMessage;
import halfandhalf.utteokMain.review.dto.ReviewDto;
import halfandhalf.utteokMain.review.entity.ReviewEntity;
import halfandhalf.utteokMain.review.repository.ReviewRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReviewService {
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

    private List<ReviewDto> transDto(List<ReviewEntity> entityList) {
        List<ReviewDto> result = new ArrayList<>();
        for (ReviewEntity reviewEntity : entityList) {
            result.add(new ReviewDto(reviewEntity));
        }
        return result;
    }
}
