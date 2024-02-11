package halfandhalf.domain.QA0010.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QA0010Dto {
    private List<QA0011Dto> question;
    private List<QA0012Dto> answer;

    @Builder
    public QA0010Dto(List<QA0011Dto> qA0011Dto, List<QA0012Dto> qA0012Dto) {
        this.question = qA0011Dto;
        this.answer = qA0012Dto;
    }
}
