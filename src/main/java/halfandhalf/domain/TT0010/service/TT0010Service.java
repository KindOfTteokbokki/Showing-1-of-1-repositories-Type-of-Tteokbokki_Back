package halfandhalf.domain.TT0010.service;

import halfandhalf.domain.TT0010.dto.TT0010Dto;
import halfandhalf.domain.TT0010.dto.TT0012Dto;

import java.util.List;

public interface TT0010Service {
    TT0010Dto findTitle(TT0010Dto tT0010Dto, Long user_id) throws Exception;

    List<TT0010Dto> findHaveTitle(Long userId);

    List<TT0012Dto> findAllTitleNotHave(Long userId);

    TT0012Dto findCountTitle(Long userId);
}
