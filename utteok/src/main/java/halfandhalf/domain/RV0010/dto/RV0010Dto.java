package halfandhalf.domain.RV0010.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RV0010Dto {
    private String content;
    private String file_path;
    private String file_original_name;
    private String file_masking_name;

    @Builder
    public RV0010Dto(String file_path, String file_original_name, String file_masking_name) {
        this.file_path = file_path;
        this.file_original_name = file_original_name;
        this.file_masking_name = file_masking_name;
    }

    @Override
    public String toString() {
        return "RV0010Dto{" +
                "content='" + content + '\'' +
                ", file_path='" + file_path + '\'' +
                ", file_original_name='" + file_original_name + '\'' +
                ", file_masking_name='" + file_masking_name + '\'' +
                '}';
    }
}
