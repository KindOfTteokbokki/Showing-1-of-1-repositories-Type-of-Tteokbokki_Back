package halfandhalf.utteokMain.loading.dto;

import lombok.Getter;

@Getter
public class LoadingDto {
    private String phrases;

    public LoadingDto(String phrases) {
        this.phrases = phrases;
    }
}
