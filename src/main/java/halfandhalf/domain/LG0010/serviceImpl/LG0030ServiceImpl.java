package halfandhalf.domain.LG0010.serviceImpl;

import halfandhalf.com.config.ResponseMessage;
import halfandhalf.com.exception.ValidationException;
import halfandhalf.com.util.Validation;
import halfandhalf.domain.LG0010.dao.LG0030Dao;
import halfandhalf.domain.LG0010.dto.LG0020Dto;
import halfandhalf.domain.LG0010.dto.LG0030Dto;
import halfandhalf.domain.LG0010.service.LG0030Service;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LG0030ServiceImpl implements LG0030Service {
    private final LG0030Dao memberRepository;

    public LG0030ServiceImpl(LG0030Dao memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean checkIfEnabledNickName(String nickname) throws ValidationException {
        validation(nickname);
        return Optional.ofNullable(memberRepository.checkIfEnabledNickName(nickname))
                .map(LG0030Dto::getNickname)
                .isPresent();
    }

    @Override
    public void registNickname(LG0020Dto lg0020Dto) throws ValidationException {
        validation(lg0020Dto.getUtteok_nickname());
        memberRepository.registNickname(lg0020Dto);
    }

    @Override
    public boolean userCheckNickName(Long userId) {
        return Optional.ofNullable(memberRepository.userCheckNickName(userId))
                .map(LG0020Dto::getNickname)
                .isPresent();
    }

    private void validation(String name) throws ValidationException {
        if(!(name).equals(Validation.Nickname(name))) {
            throw new ValidationException(ResponseMessage.valueOfCode("Validation").getMessage());
        }
    }
}