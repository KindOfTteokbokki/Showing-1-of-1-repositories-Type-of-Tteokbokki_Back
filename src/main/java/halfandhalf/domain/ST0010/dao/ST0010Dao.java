package halfandhalf.domain.ST0010.dao;

import halfandhalf.domain.ST0010.dto.ST0010Dto;
import halfandhalf.domain.ST0010.dto.ST0011Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ST0010Dao {
    ST0010Dto findOneFromMyTaste(ST0010Dto st0010Dto);

    ST0010Dto findStore(ST0010Dto sT0010Dto);

    List<ST0011Dto> findMyTasteById(ST0011Dto st0011Dto);

    ST0011Dto findMyTasteByIdSeq(ST0011Dto st0011Dto);

    List<ST0010Dto> findMyTasteByCount(Long user_id);

    void updateStoreCount(ST0011Dto st0011Dto);

    void insertStoreCount(ST0011Dto st0011Dto);
}
