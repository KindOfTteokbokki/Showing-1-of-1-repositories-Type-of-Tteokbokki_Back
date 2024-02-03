package halfandhalf.domain.TT0010.serviceImpl;

import halfandhalf.domain.ST0010.dto.ST0010Dto;
import halfandhalf.domain.TT0010.dao.TT0010Dao;
import halfandhalf.domain.TT0010.dto.TT0010Dto;
import halfandhalf.domain.TT0010.service.TT0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public class TT0010ServiceImpl implements TT0010Service {

    private final TT0010Dao tT0010Dao;

    @Autowired
    public TT0010ServiceImpl(TT0010Dao tT0010Dao) {
        this.tT0010Dao = tT0010Dao;
    }

    @Override
    @Transactional(readOnly = true)
    public TT0010Dto findTitle(TT0010Dto tT0010Dto) throws Exception {
        TT0010Dto store = tT0010Dao.findTitle(tT0010Dto);
//        if(ObjectUtils.isEmpty(store)) {
//            tT0010Dto = new TT0010Dto();
//            store = tT0010Dao.findTitle(tT0010Dto);
//        }
        return store;
    }
}
