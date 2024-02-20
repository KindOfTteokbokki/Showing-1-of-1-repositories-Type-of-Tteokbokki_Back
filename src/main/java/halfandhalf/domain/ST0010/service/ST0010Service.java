package halfandhalf.domain.ST0010.service;

import halfandhalf.domain.ST0010.dto.ST0010Dto;

import java.util.List;

public interface ST0010Service {
    ST0010Dto findStore(ST0010Dto sT0010Dto) throws Exception;

    List<ST0010Dto> findByCount();

    ST0010Dto findOneFromMyTaste(ST0010Dto st0010Dto);
}
