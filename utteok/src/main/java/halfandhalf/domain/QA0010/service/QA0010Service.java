package halfandhalf.domain.QA0010.service;


import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;

import java.util.List;

public interface QA0010Service {

    List<QA0011Dto> findQuestion() throws Exception;

    List<QA0012Dto> findAnswer() throws Exception;
}
