package halfandhalf.domain.LG0010.dao;

import halfandhalf.domain.LG0010.dto.LG0020Dto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
// LoginMapper
public interface LG0020Dao {
    LG0020Dto findById(@Param("id") Long id);
    LG0020Dto findByNickname(@Param("nickname") String nickname);
    void save(LG0020Dto member);
}
