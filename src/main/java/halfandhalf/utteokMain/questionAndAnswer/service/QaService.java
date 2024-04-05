package halfandhalf.utteokMain.questionAndAnswer.service;

import halfandhalf.utteokMain.questionAndAnswer.dto.QaDto;
import halfandhalf.utteokMain.questionAndAnswer.entity.QaEntity;
import halfandhalf.utteokMain.questionAndAnswer.repository.QaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class QaService {
    private final QaRepository qaRepository;

    public QaService(QaRepository qaRepository) {
        this.qaRepository = qaRepository;
    }

    public List<QaDto> findByCodeIdAndUseYn(String code_id, String use_yn) {

        List<QaEntity> qaEntityList = qaRepository.findByCodeIdAndUseYn(code_id, use_yn);
        List<QaDto> qaDtoList = new ArrayList<>();
        for (QaEntity qaEntity : qaEntityList) {
            qaDtoList.add(new QaDto(qaEntity));
        }

        return qaDtoList;
    }
}
