package halfandhalf.domain.ST0010.dto;

import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ST0010Dto {
    private int store_seq;
    private int menu_count;
    private String store_name;
    private String store_address;
    private String menu_name;
    private String review;
    private String file_path;
    private String file_original_name;
    private String file_masking_name;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String question7;

    public ST0010Dto() {
    }

    public ST0010Dto(String question1, String question2, String question3, String question4, String question5, String question6, String question7) {
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.question7 = question7;
    }
}
