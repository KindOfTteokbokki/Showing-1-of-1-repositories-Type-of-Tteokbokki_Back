package halfandhalf.utteokMain.member.dto;

import lombok.Getter;

@Getter
public class MemberDto {
    private String phrases;

    public MemberDto(String phrases) {
        this.phrases = phrases;
    }
}
