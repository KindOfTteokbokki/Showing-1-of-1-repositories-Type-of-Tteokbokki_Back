package halfandhalf.utteokMain.member.entity;

import halfandhalf.utteokMain.member.dto.MemberRoute;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "oauth_login")
@Getter
public class MemberEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String nickname;

    private String utteok_nickname;

    @Enumerated(EnumType.STRING)
    private MemberRoute oAuthProvider;

    private LocalDateTime create_date;

    private LocalDateTime recent_date;

    public void changeUtteok_nickname(String utteok_nickname) {
        this.utteok_nickname = utteok_nickname;
    }

    @Builder
    public MemberEntity(String email, String nickname, MemberRoute oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }
}
