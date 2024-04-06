package halfandhalf.utteokMain.review.dto;


import halfandhalf.utteokMain.review.entity.ReviewEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private Long id;

    private String content;
    private String create_date;

    private String file_path;
    private String file_original_name;

    public ReviewDto() {
    }

    public ReviewDto(ReviewEntity entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.create_date = entity.getCreate_date();
        this.file_path = entity.getFileManage().getFile_path();
        this.file_original_name = entity.getFileManage().getFile_original_name();
    }
}
