package halfandhalf.domain.LD0010.serviceImpl;

import halfandhalf.domain.LD0010.dao.LD0010Dao;
import halfandhalf.domain.LD0010.dto.LD0010Dto;
import halfandhalf.domain.LD0010.service.LD0010Service;
import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LD0010ServiceImpl implements LD0010Service {

    private final LD0010Dao lD0010Dao;

    public LD0010ServiceImpl(LD0010Dao lD0010Dao) {
        this.lD0010Dao = lD0010Dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LD0010Dto> findLoading(){
        return lD0010Dao.findLoading();
    }
}
