package halfandhalf.domain.CB0010.serviceImpl;

import halfandhalf.domain.CB0010.dao.CB0010Dao;
import halfandhalf.domain.CB0010.dto.CB0010Dto;
import halfandhalf.domain.CB0010.service.CB0010Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CB0010ServiceImpl implements CB0010Service {

    private final CB0010Dao cB0010Dao;

    @Override
    @Transactional(readOnly = true)
    public CB0010Dto findOneFromCombination(CB0010Dto cb0010Dto) {
        return cB0010Dao.findOneFromCombination(cb0010Dto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CB0010Dto> findCombination() {
        return cB0010Dao.findCombination();
    }
}
