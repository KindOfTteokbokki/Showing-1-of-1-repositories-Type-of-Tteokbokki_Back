package halfandhalf.domain.LD0010.dao;

import halfandhalf.domain.LD0010.dto.LD0010Dto;
import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface LD0010Dao {

    List<LD0010Dto> findLoading();

}
