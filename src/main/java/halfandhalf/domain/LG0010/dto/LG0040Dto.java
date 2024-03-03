package halfandhalf.domain.LG0010.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class LG0040Dto {
    private String name;
    private String ip;

    public LG0040Dto(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }
}
