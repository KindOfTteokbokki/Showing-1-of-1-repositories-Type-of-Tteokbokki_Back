package halfandhalf.utteokMain.main.title.repository;

import halfandhalf.utteokMain.main.commonDto.QuestionDto;
import halfandhalf.utteokMain.main.title.entity.TitleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, Long> {

    TitleEntity findTitle(@RequestBody QuestionDto dto);

    TitleEntity findTitleByIdAndSeq(@Param("user_id") long id, @Param("title_seq") Long seq);
}
