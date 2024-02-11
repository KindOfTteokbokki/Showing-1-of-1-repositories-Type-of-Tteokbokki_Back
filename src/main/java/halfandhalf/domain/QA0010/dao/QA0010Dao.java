package halfandhalf.domain.QA0010.dao;

import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface QA0010Dao {

    List<QA0011Dto> findQuestion();

    List<QA0012Dto> findAnswer();

}
