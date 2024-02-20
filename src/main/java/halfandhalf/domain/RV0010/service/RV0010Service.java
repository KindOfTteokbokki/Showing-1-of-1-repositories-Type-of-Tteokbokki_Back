package halfandhalf.domain.RV0010.service;

import halfandhalf.domain.RV0010.dto.RV0010Dto;
import halfandhalf.domain.RV0010.dto.RV0011Dto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RV0010Service {

    RV0010Dto findOneFromRecommend(RV0010Dto rv0010Dto);

    void saveRecommend(RV0010Dto rv0010Dto, MultipartFile file) throws Exception;

    List<RV0010Dto> findRecommendByPage(RV0011Dto rv0011Dto, Long user_id);
}
