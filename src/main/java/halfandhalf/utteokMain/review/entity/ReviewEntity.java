package halfandhalf.utteokMain.review.entity;


import halfandhalf.utteokMain.manageEntity.FileManageEntity;
import halfandhalf.utteokMain.review.dto.ReviewDto;
import lombok.Getter;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "review_for_store")
public class ReviewEntity {
    @Id @GeneratedValue
    @Column(name = "review_seq")
    private Long id;

    private String content;
    private Long user_id;
    private LocalDateTime create_date;

    @Embedded
    private FileManageEntity fileManage;

    public ReviewEntity() {
    }

    public void changeData(ReviewDto dto) {
        //id, content, path, original, masking
        this.id = dto.getId();
        this.content = dto.getContent();
        if(StringUtils.hasText(dto.getFile_path()))
            this.fileManage = new FileManageEntity(dto.getFile_path(), dto.getFile_original_name(), dto.getFile_masking_name());
    }

    public ReviewEntity(ReviewDto dto) {
        this.content = dto.getContent();
        this.user_id = dto.getId();
        if(StringUtils.hasText(dto.getFile_path()))
            this.fileManage = new FileManageEntity(dto.getFile_path(), dto.getFile_original_name(), dto.getFile_masking_name());
    }
}
