package halfandhalf.domain.RV0010.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RV0011Dto {
    private int pageNum;
    private int size;

    public RV0011Dto() {
        this(1, 15);
    }

    public RV0011Dto(int pageNum, int size) {
        this.pageNum = pageNum;
        this.size = size;
    }
}