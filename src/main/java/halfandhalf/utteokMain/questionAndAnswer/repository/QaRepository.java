package halfandhalf.utteokMain.questionAndAnswer.repository;

import halfandhalf.utteokMain.questionAndAnswer.entity.QaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QaRepository extends JpaRepository<QaEntity, Long>  {

    List<QaEntity> findByCodeIdAndUseYn(@Param("code_id") String code_id, @Param("use_yn") String use_yn);

}
