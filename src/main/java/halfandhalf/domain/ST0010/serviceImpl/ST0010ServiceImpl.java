package halfandhalf.domain.ST0010.serviceImpl;

import halfandhalf.domain.ST0010.dao.ST0010Dao;
import halfandhalf.domain.ST0010.dto.ST0010Dto;
import halfandhalf.domain.ST0010.dto.ST0011Dto;
import halfandhalf.domain.ST0010.service.ST0010Service;
import halfandhalf.domain.TT0010.dto.TT0010Dto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ST0010ServiceImpl implements ST0010Service {

    private final ST0010Dao sT0010Dao;

    public ST0010ServiceImpl(ST0010Dao sT0010Dao) {
        this.sT0010Dao = sT0010Dao;
    }

    @Override
    public ST0010Dto findOneFromMyTaste(ST0010Dto st0010Dto) {
        return sT0010Dao.findOneFromMyTaste(st0010Dto);
    }

    @Override
    @Transactional
    public ST0010Dto findStore(ST0010Dto sT0010Dto, Long user_id) throws Exception {
        final ST0010Dto[] store = {null};

        Optional.ofNullable(sT0010Dao.findStore(sT0010Dto))
                .ifPresentOrElse(
                        is -> {
                            store[0] = is;
                        }
                        , () -> {
                            store[0] = sT0010Dao.findStore(new ST0010Dto("ch100", "ch200", "ch300", "ch400", "ch500", "ch600", "ch700"));
                        }
                );

        if(0 != user_id.intValue()) {
            ST0011Dto st0011Dto = new ST0011Dto(user_id, store[0].getStore_seq());
            Optional.ofNullable(sT0010Dao.findMyTasteByIdSeq(st0011Dto))
                    .ifPresentOrElse(
                            getST0011Dto->{
                                getST0011Dto.setMenu_count(getST0011Dto.getMenu_count() + 1);
//                                getST0011Dto.addMenuCount();
                                sT0010Dao.updateStoreCount(getST0011Dto);
                            }
                            ,()->{
                                st0011Dto.setMenu_count(1);
//                                st0011Dto.addMenuCount();
                                sT0010Dao.insertStoreCount(st0011Dto);
                            });
        }
        return store[0];
    }

    @Override
    public List<ST0010Dto> findMyTasteByCount(Long user_id) {
        return sT0010Dao.findMyTasteByCount(user_id);
    }
}
