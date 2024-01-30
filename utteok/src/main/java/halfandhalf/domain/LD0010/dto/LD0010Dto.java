package halfandhalf.domain.LD0010.dto;

import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LD0010Dto {
    private String phrases;
    private String file_path;
    private String file_original_name;
    private String file_masking_name;
}
