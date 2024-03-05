package halfandhalf.domain.LG0010.service;

import halfandhalf.com.exception.ValidationException;
import halfandhalf.domain.LG0010.dto.LG0020Dto;

public interface LG0030Service {
    boolean checkIfEnabledNickName(String nickname) throws ValidationException;

    void registNickname(LG0020Dto lg0020Dto) throws ValidationException;

    boolean userCheckNickName(Long userId);
}
