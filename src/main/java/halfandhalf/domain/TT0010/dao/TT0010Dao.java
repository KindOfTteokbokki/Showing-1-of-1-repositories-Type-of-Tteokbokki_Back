package halfandhalf.domain.TT0010.dao;

import halfandhalf.domain.ST0010.dto.ST0011Dto;
import halfandhalf.domain.TT0010.dto.TT0010Dto;
import halfandhalf.domain.TT0010.dto.TT0011Dto;
import halfandhalf.domain.TT0010.dto.TT0012Dto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
@Mapper
public interface TT0010Dao {
    TT0010Dto findTitle(TT0010Dto tT0010Dto);

    TT0011Dto findTitleByIdSeq(TT0011Dto tT0011Dto);

    void updateTitleCount(ST0011Dto st0011Dto);

    void insertHaveTitle(TT0011Dto tT0011Dto);

    List<TT0010Dto> findHaveTitle(Long userId);

    LinkedList<TT0012Dto> findAllTitleNotHave(Long userId);

    TT0012Dto findCountTitle(Long userId);
}
