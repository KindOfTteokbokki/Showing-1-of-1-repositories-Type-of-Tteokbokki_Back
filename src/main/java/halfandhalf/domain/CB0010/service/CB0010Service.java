package halfandhalf.domain.CB0010.service;

import halfandhalf.domain.CB0010.dto.CB0010Dto;

import java.util.List;

public interface CB0010Service {
    CB0010Dto findOneFromCombination(CB0010Dto cb0010Dto);
    List<CB0010Dto> findCombination();
}
