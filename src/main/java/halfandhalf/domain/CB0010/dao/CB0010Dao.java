package halfandhalf.domain.CB0010.dao;

import halfandhalf.domain.CB0010.dto.CB0010Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CB0010Dao {
    CB0010Dto findOneFromCombination(CB0010Dto cb0010Dto);
    List<CB0010Dto> findCombination();
}
