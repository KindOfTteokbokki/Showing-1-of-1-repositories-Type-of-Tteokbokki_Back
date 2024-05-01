package halfandhalf.utteokMain.questionAndAnswer.entity;

import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "public_code")
@Getter
public class QaEntity implements Serializable {
    @Id
    private String code;

    @Column(name = "name_ko")
    private String name;

    @Column(name = "code_category_id")
    private String codeId;

    @Column(name = "use_yn")
    private String useYn;

    public QaEntity() {
    }

    public QaEntity(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
