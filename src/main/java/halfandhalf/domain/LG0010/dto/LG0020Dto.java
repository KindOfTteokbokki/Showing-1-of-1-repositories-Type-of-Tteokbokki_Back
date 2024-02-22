package halfandhalf.domain.LG0010.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@NoArgsConstructor
public class LG0020Dto {

    private Long id;

    private String email;

    private String nickname;

    @Setter
    private String utteok_nickname;

    private LG0021Dto oAuthProvider;

    private Date create_date;

    private Date recent_date;

    @Builder
    public LG0020Dto(String email, String nickname, LG0021Dto oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }

}