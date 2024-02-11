package halfandhalf.domain.TT0010.dao;

import halfandhalf.domain.TT0010.dto.TT0010Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TT0010Dao {
    TT0010Dto findTitle(TT0010Dto tT0010Dto);
}
