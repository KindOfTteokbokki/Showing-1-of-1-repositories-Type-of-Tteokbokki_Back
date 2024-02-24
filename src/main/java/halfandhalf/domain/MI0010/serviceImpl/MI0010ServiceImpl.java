package halfandhalf.domain.MI0010.serviceImpl;

import halfandhalf.domain.MI0010.dao.MI0010Dao;
import halfandhalf.domain.MI0010.dto.MI0010Dto;
import halfandhalf.domain.MI0010.service.MI0010Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MI0010ServiceImpl implements MI0010Service {

    private final MI0010Dao mi0010Dao;

    @Override
    @Transactional(readOnly = true)
    public String findMyInfo(Long userId) throws Exception {
        Optional<MI0010Dto> mi0010Dto = Optional.ofNullable(mi0010Dao.findMyInfo(userId));

        return Optional.ofNullable(mi0010Dao.findMyInfo(userId))
                .map(MI0010Dto::getUtteok_nickname)
                .orElseGet(()->mi0010Dto.get().getNickname());
    }
}
