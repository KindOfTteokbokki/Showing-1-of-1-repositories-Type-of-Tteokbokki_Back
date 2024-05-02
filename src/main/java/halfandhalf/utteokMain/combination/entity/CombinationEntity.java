package halfandhalf.utteokMain.combination.entity;


import halfandhalf.utteokMain.manageEntity.FileManageEntity;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "utteok_combination")
public class CombinationEntity implements Serializable {
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
