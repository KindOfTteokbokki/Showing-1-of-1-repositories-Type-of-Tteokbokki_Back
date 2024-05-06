package halfandhalf.utteokMain.main.title.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TitleDto {
    private Long title_seq;
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
    private int title_count;
    private Long user_id;
    private boolean get_title;
}
