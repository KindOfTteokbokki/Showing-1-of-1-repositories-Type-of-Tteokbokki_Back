package halfandhalf.domain.ST0010.serviceImpl;

import halfandhalf.domain.ST0010.dao.ST0010Dao;
import halfandhalf.domain.ST0010.dto.ST0010Dto;
import halfandhalf.domain.ST0010.dto.ST0011Dto;
import halfandhalf.domain.ST0010.service.ST0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ST0010ServiceImpl implements ST0010Service {

    private final ST0010Dao sT0010Dao;

    @Autowired
    public ST0010ServiceImpl(ST0010Dao sT0010Dao) {
        this.sT0010Dao = sT0010Dao;
    }

    @Override
    @Transactional(readOnly = true)
    public ST0010Dto findOneFromMyTaste(ST0010Dto st0010Dto) {
        return sT0010Dao.findOneFromMyTaste(st0010Dto);
    }

    @Override
    @Transactional
    public ST0010Dto findStore(ST0010Dto sT0010Dto, Long user_id) throws Exception {
        ST0010Dto store = sT0010Dao.findStore(sT0010Dto);

        if(0 != user_id.intValue()) {
            ST0011Dto st0011Dto = new ST0011Dto(user_id, store.getStore_seq());

            Optional<ST0011Dto> optional = Optional.ofNullable(sT0010Dao.findMyTasteByIdSeq(st0011Dto));

            if(optional.isPresent()) {
                optional.ifPresent(dao -> dao.setMenu_count(dao.getMenu_count() + 1));
                sT0010Dao.updateStoreCount(optional.get());
            } else {
                st0011Dto.setMenu_count(1);
                sT0010Dao.insertStoreCount(st0011Dto);
            }
        }
        return store;
    }

    @Override
    @Transactional
    public List<ST0010Dto> findMyTasteByCount(Long user_id) {
        return sT0010Dao.findMyTasteByCount(user_id);
    }
}
