package halfandhalf.domain.ST0010.dao;

import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;
import halfandhalf.domain.ST0010.dto.ST0010Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ST0010Dao {
    ST0010Dto findStore(ST0010Dto sT0010Dto);

    void updateStoreCount(ST0010Dto store);

    List<ST0010Dto> findByCount();
}
