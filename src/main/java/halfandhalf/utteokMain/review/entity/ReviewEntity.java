package halfandhalf.utteokMain.review.entity;


import halfandhalf.utteokMain.manageEntity.FileManageEntity;
import halfandhalf.utteokMain.review.dto.ReviewDto;
import lombok.Getter;

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

//    public ReviewEntity(Long id, String content, String user_id, LocalDateTime create_date, FileManageEntity fileManage) {
//        this.id = id;
//        this.content = content;
//        this.user_id = user_id;
//        this.create_date = create_date;
//        this.fileManage = fileManage;
//    }

    public ReviewEntity(ReviewDto dto) {
        this.content = dto.getContent();
        this.user_id = dto.getId();
        this.fileManage = new FileManageEntity(dto.getFile_path(), dto.getFile_original_name(), dto.getFile_masking_name());
    }
}
