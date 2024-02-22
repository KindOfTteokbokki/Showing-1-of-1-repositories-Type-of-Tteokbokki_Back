package halfandhalf.domain.MI0010.serviceImpl;

import halfandhalf.domain.MI0010.dao.MI0010Dao;
import halfandhalf.domain.MI0010.dto.MI0010Dto;
import halfandhalf.domain.MI0010.service.MI0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class MI0010ServiceImpl implements MI0010Service {

    private final MI0010Dao mi0010Dao;

    @Autowired
    public MI0010ServiceImpl(MI0010Dao mi0010Dao) {
        this.mi0010Dao = mi0010Dao;
    }

    @Override
    @Transactional(readOnly = true)
    public String findMyInfo(Long userId) throws Exception {
        Optional<MI0010Dto> mi0010Dto = Optional.ofNullable(mi0010Dao.findMyInfo(userId));

        if(mi0010Dto.map(MI0010Dto::getUtteok_nickname).isPresent()) {
            return mi0010Dto.get().getUtteok_nickname();
        } else {
            return mi0010Dto.get().getNickname();
        }
    }
}
