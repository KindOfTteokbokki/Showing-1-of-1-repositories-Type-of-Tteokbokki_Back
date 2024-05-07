package halfandhalf.utteokMain.main.title.repository;

import halfandhalf.utteokMain.main.title.entity.HaveTitleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HavaTitleRepository extends JpaRepository<HaveTitleEntity, Long> {
    List<HaveTitleEntity> findByUserId(@Param("userId") Long userId);

    HaveTitleEntity findByUserIdAndTitleSeq(@Param("userId") Long userId, @Param("titleSeq") Long seq);

    List<HaveTitleEntity> findByIdNotIn(List<Long> seq);
}
