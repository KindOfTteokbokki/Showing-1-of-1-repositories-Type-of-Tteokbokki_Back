package halfandhalf.domain.TT0010.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TT0010Dto {
    private int title_seq;
    private String title_name;
    private String icorn_file_path;
    private String icorn_file_original_name;
    private String icorn_file_masking_name;
    private String img_file_path;
    private String img_file_original_name;
    private String img_file_masking_name;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String question7;

    public TT0010Dto(String question1, String question2, String question3, String question4, String question5, String question6, String question7) {
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.question7 = question7;
    }
}
