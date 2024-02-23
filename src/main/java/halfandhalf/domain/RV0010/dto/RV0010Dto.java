package halfandhalf.domain.RV0010.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class RV0010Dto {
    private int review_seq;
    private Long user_id;
    private String content;
    private String file_path;
    private String file_original_name;
    private String file_masking_name;
    private Date create_date;
    private Boolean my_recommend;

    @Builder
    public void setFile(String file_path, String file_original_name, String file_masking_name) {
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
