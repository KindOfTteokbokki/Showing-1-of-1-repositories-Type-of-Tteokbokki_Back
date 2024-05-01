package halfandhalf.utteokMain.questionAndAnswer.dto;

import halfandhalf.utteokMain.questionAndAnswer.entity.QaEntity;
import lombok.Getter;

@Getter
public class QaDto {
    private String code;
    private String name;

    public QaDto(QaEntity qaEntity) {
        this.code = qaEntity.getCode();
        this.name = qaEntity.getName();
    }

    public QaDto() {
    }
}
