package halfandhalf.domain.TT0010.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TT0011Dto {
    private Long user_id;
    private int title_seq;
    private boolean get_title;

    public TT0011Dto() {
    }

    public TT0011Dto(Long user_id, int title_seq) {
        this.user_id = user_id;
        this.title_seq = title_seq;
    }
}
