package halfandhalf.utteokMain.main.store.entity;

import halfandhalf.utteokMain.manageEntity.FileManageEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "menu_img")
public class MenuImgEntity {
    @Id
    @GeneratedValue
    @Column(name = "menu_img_seq")
    private Long id;

    private Long question_seq;

    @Embedded
    FileManageEntity fileManageEntity;
}