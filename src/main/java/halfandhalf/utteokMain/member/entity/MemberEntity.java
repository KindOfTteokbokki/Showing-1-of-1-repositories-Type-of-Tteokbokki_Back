package halfandhalf.utteokMain.member.entity;

import halfandhalf.domain.LG0010.dto.LG0021Dto;
import halfandhalf.utteokMain.member.dto.MemberRoute;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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

    private MemberRoute oAuthProvider;

    private LocalDateTime create_date;

    private LocalDateTime recent_date;

    @Builder
    public MemberEntity(String email, String nickname, MemberRoute oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }
}
