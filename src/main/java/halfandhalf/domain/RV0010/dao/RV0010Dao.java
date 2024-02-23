package halfandhalf.domain.RV0010.dao;

import halfandhalf.domain.RV0010.dto.RV0010Dto;
import halfandhalf.domain.RV0010.dto.RV0011Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RV0010Dao {
    RV0010Dto findOneFromRecommend(RV0010Dto rv0010Dto);

    void saveRecommend(RV0010Dto rv0010Dto);

    List<RV0010Dto> findRecommendByPage(RV0011Dto rv0011Dto);

    void modifyRecommend(RV0010Dto rv0010Dto);

    void deleteRecommend(RV0010Dto rv0010Dto);
}
