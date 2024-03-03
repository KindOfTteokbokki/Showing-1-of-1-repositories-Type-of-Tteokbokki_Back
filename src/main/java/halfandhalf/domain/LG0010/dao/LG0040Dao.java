package halfandhalf.domain.LG0010.dao;


import halfandhalf.domain.LG0010.dto.LG0040Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LG0040Dao {
    LG0040Dto checkIp(LG0040Dto lg0040Dto);
    void updateDateIp(LG0040Dto lg0040Dto);
    void regIp(LG0040Dto lg0040Dto);
}
