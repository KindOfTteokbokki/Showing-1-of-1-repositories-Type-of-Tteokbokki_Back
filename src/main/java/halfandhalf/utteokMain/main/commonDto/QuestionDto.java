package halfandhalf.utteokMain.main.commonDto;

import halfandhalf.domain.ST0010.dto.ST0010Dto;
import lombok.Getter;
import lombok.Setter;

@Getter
public class QuestionDto {
    private Long question_seq;

    private String category;
    private String code;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String question7;

    public QuestionDto() {
    }

    public QuestionDto(String question1, String question2, String question3, String question4, String question5, String question6, String question7) {
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.question7 = question7;
    }

    public static QuestionDto getInstance() {
        return new QuestionDto("ch100", "ch200", "ch300", "ch400", "ch500", "ch600", "ch700");
    }
}
