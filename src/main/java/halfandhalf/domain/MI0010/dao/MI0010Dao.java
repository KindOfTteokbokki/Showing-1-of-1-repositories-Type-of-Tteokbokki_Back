package halfandhalf.domain.MI0010.dao;

import halfandhalf.domain.MI0010.dto.MI0010Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MI0010Dao {

    MI0010Dto findMyInfo(Long userId);

}
