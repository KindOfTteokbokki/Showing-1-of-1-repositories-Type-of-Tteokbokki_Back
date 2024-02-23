package halfandhalf.domain.LG0010.dao;

import halfandhalf.domain.LG0010.dto.LG0030Dto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LG0030Dao {
    LG0030Dto checkIfEnabledNickName(@Param("utteok_nickname") String nickname);
}
