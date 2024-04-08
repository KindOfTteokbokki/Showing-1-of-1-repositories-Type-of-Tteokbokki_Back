package halfandhalf.utteokMain.review.repository;

import halfandhalf.utteokMain.review.entity.ReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
//    @Query("select r from ReviewEntity r order by review_seq limit 4")
//    List<ReviewEntity> findOrderByIdDesc();
    List<ReviewEntity> findTop4ByOrderByIdDesc();

    Slice<ReviewEntity> findSliceByOrderByIdDesc(Pageable pageable);
}
