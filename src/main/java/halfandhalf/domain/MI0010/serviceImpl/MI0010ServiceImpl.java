package halfandhalf.domain.MI0010.serviceImpl;

import halfandhalf.domain.MI0010.dao.MI0010Dao;
import halfandhalf.domain.MI0010.dto.MI0010Dto;
import halfandhalf.domain.MI0010.service.MI0010Service;
import halfandhalf.domain.ST0010.dto.ST0010Dto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class MI0010ServiceImpl implements MI0010Service {

    private final MI0010Dao mi0010Dao;

    public MI0010ServiceImpl(MI0010Dao mi0010Dao) {
        this.mi0010Dao = mi0010Dao;
    }

    @Override
    @Transactional(readOnly = true)
    public String findMyInfo(Long userId) throws Exception {
        MI0010Dto myInfo = mi0010Dao.findMyInfo(userId);
        return StringUtils.hasText(myInfo.getUtteok_nickname()) ? myInfo.getUtteok_nickname() : myInfo.getNickname();
    }
}
