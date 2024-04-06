package halfandhalf.utteokMain.review.service;

import halfandhalf.com.config.ResponseMessage;
import halfandhalf.utteokMain.review.dto.ReviewDto;
import halfandhalf.utteokMain.review.entity.ReviewEntity;
import halfandhalf.utteokMain.review.repository.ReviewRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
