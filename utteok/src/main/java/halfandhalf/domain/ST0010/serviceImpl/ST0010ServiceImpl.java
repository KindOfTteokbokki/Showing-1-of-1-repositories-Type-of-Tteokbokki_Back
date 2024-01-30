package halfandhalf.domain.ST0010.serviceImpl;

import halfandhalf.domain.ST0010.dao.ST0010Dao;
import halfandhalf.domain.ST0010.dto.ST0010Dto;
import halfandhalf.domain.ST0010.service.ST0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class ST0010ServiceImpl implements ST0010Service {

    private final ST0010Dao sT0010Dao;

    @Autowired
    public ST0010ServiceImpl(ST0010Dao sT0010Dao) {
        this.sT0010Dao = sT0010Dao;
    }

    @Override
    @Transactional(readOnly = true)
    public ST0010Dto findStore(ST0010Dto sT0010Dto) throws Exception {
        ST0010Dto store = sT0010Dao.findStore(sT0010Dto);
        if(ObjectUtils.isEmpty(store)) {
            sT0010Dto = new ST0010Dto();
            store = sT0010Dao.findStore(sT0010Dto);
        } else {
            store.setCount_store(store.getCount_store() + 1);
            sT0010Dao.updateStoreCount(store);
        }
        return store;
    }

    @Override
    public List<ST0010Dto> findByCount() {
        return sT0010Dao.findByCount();
    }
}
