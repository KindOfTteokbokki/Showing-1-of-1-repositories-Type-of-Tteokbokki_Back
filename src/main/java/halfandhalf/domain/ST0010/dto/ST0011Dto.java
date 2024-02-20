package halfandhalf.domain.ST0010.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ST0011Dto {
    private Long user_id;
    private int store_seq;
    private int menu_count;

    public ST0011Dto(Long user_id, int store_seq) {
        this.user_id = user_id;
        this.store_seq = store_seq;
    }
}
