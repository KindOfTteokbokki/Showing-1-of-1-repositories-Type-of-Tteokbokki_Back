package halfandhalf.utteokMain.main.title.entity;

import halfandhalf.utteokMain.manageEntity.FileManageEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "title_img")
public class TitleImgEntity {
    @Id @GeneratedValue
    @Column(name = "title_img_seq")
    private Long id;

    private String title_code;

    @Embedded
    @AttributeOverrides({
              @AttributeOverride(name = "file_path", column = @Column(name = "icorn_file_path"))
            , @AttributeOverride(name = "file_original_name", column = @Column(name = "icorn_file_original_name"))
            , @AttributeOverride(name = "file_masking_name", column = @Column(name = "icorn_file_masking_name"))
    })
    private FileManageEntity icornFileManageEntity;

    @Embedded
    @AttributeOverrides({
              @AttributeOverride(name = "file_path", column = @Column(name = "img_file_path"))
            , @AttributeOverride(name = "file_original_name", column = @Column(name = "img_file_original_name"))
            , @AttributeOverride(name = "file_masking_name", column = @Column(name = "img_file_masking_name"))
    })
    private FileManageEntity imgFfileManageEntity;
}
