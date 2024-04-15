package halfandhalf.utteokMain.review.entity;


import halfandhalf.utteokMain.manageEntity.FileManageEntity;
import halfandhalf.utteokMain.review.dto.ReviewDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "review_for_store")
public class ReviewEntity {
    @Id @GeneratedValue
    @Column(name = "review_seq")
    private Long id;

    private String content;
    private String user_id;
    private String create_date;

    @Embedded
    private FileManageEntity fileManage;

    public ReviewEntity() {
    }

    public ReviewEntity(Long id, String content, String user_id, String create_date, FileManageEntity fileManage) {
        this.id = id;
        this.content = content;
        this.user_id = user_id;
        this.create_date = create_date;
        this.fileManage = fileManage;
    }

    public ReviewEntity(ReviewDto upload) {
    }
}
