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
    private String store_name;
    private String store_address;
    private String menu_name;
    private String review;
    private String file_path;
    private String file_original_name;
    private String file_masking_name;
    private int count_store;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private String question6;
    private String question7;
}
