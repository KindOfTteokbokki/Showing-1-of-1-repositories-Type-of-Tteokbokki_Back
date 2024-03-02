package halfandhalf.domain.LG0010.service;

import halfandhalf.domain.LG0010.dto.LG0020Dto;

public interface LG0030Service {
    boolean checkIfEnabledNickName(String nickname);

    void registNickname(LG0020Dto lg0020Dto);
}
