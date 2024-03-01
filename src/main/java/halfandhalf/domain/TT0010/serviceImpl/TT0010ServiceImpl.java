package halfandhalf.domain.TT0010.serviceImpl;

import halfandhalf.domain.TT0010.dao.TT0010Dao;
import halfandhalf.domain.TT0010.dto.TT0010Dto;
import halfandhalf.domain.TT0010.dto.TT0011Dto;
import halfandhalf.domain.TT0010.dto.TT0012Dto;
import halfandhalf.domain.TT0010.service.TT0010Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TT0010ServiceImpl implements TT0010Service {

    private final TT0010Dao tT0010Dao;

    @Override
    @Transactional
    public TT0010Dto findTitle(TT0010Dto tT0010Dto, Long user_id) throws Exception {
        final TT0010Dto[] title = {null};

        Optional.ofNullable(tT0010Dao.findTitle(tT0010Dto))
                .ifPresentOrElse(
                          is -> {
                              title[0] = is;
                          }
                        , () -> {
                            title[0] = tT0010Dao.findTitle(new TT0010Dto("ch100", "ch200", "ch300", "ch400", "ch500", "ch600", "ch700"));
                        }
                );

        if(0 != user_id.intValue()) {
            TT0011Dto tT0011Dto = new TT0011Dto(user_id, title[0].getTitle_seq());
            Optional.ofNullable(tT0010Dao.findTitleByIdSeq(tT0011Dto))
                    .ifPresentOrElse(
                            is -> {
                                System.out.println("have it");
                            }
                            , () -> {
                                tT0011Dto.setGet_title(true);
                                tT0010Dao.insertHaveTitle(tT0011Dto);
                            }
                    );
        }
        return title[0];
    }

    @Override
    @Transactional(readOnly = true)
    public List<TT0010Dto> findHaveTitle(Long userId) {
        return tT0010Dao.findHaveTitle(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TT0012Dto> findAllTitleNotHave(Long userId) {
        return tT0010Dao.findAllTitleNotHave(userId);
    }

    @Override
    public TT0012Dto findCountTitle(Long userId) {
        return tT0010Dao.findCountTitle(userId);
    }
}
