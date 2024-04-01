package halfandhalf.utteokMain.loading.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
public class LoadingDto {
    private String phrases;

    public LoadingDto(String phrases) {
        this.phrases = phrases;
    }
}
