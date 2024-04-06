package halfandhalf.utteokMain.review.repository;

import halfandhalf.utteokMain.review.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

}
