package halfandhalf.domain.QA0010.serviceImpl;

import halfandhalf.domain.QA0010.dao.QA0010Dao;
import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;
import halfandhalf.domain.QA0010.service.QA0010Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QA0010ServiceImpl implements QA0010Service {

    private final QA0010Dao qA0010Dao;

    @Autowired
    public QA0010ServiceImpl(QA0010Dao qA0010Dao) {
        this.qA0010Dao = qA0010Dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QA0011Dto> findQuestion() {
        return qA0010Dao.findQuestion();
    }

    @Override
    @Transactional(readOnly = true)
    public List<QA0012Dto> findAnswer() {
        return qA0010Dao.findAnswer();
    }
}
