package halfandhalf.utteokMain.combination.entity;


import halfandhalf.utteokMain.manageEntity.FileManageEntity;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Table(name = "utteok_combination")
public class CombinationEntity {
    @Id @GeneratedValue
    @Column(name = "combination_seq")
    private Long id;

    @NotEmpty
    private String content;

    @Embedded
    private FileManageEntity fileManageEntity;

    public CombinationEntity() {
    }

    public CombinationEntity(Long id, String content, FileManageEntity fileManageEntity) {
        this.id = id;
        this.content = content;
        this.fileManageEntity = fileManageEntity;
    }
}
