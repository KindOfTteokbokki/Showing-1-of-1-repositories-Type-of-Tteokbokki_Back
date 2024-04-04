package halfandhalf.utteokMain.member.service;

import halfandhalf.com.config.ResponseMessage;
import halfandhalf.com.exception.ValidationException;
import halfandhalf.com.util.Validation;
import halfandhalf.utteokMain.member.dto.MemberDto;
import halfandhalf.utteokMain.member.entity.MemberEntity;
import halfandhalf.utteokMain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberApiService {
    private final MemberRepository memberRepository;

    public MemberApiService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean checkIfEnabledNickName(String nickname) throws ValidationException {
        validation(nickname);
        return Optional.ofNullable(memberRepository.findByUtteokNickname(nickname))
                .map(MemberEntity::getNickname)
                .isPresent();
    }

    @Transactional
    public void registNickname(MemberDto memberDto) throws ValidationException {
        validation(memberDto.getUtteok_nickname());

        if(!checkIfEnabledNickName(memberDto.getUtteok_nickname())){
            memberRepository.findById(memberDto.getId())
                    .ifPresent(value -> {
                        value.changeUtteok_nickname(memberDto.getUtteok_nickname());
                    });
        } else {
            throw new ValidationException(ResponseMessage.valueOfCode("Conflict").getMessage());
        }
    }

    public boolean userCheckNickName(Long userId) {
        return Optional.ofNullable(memberRepository.findUtteokNicknameById(userId))
                .map(MemberEntity::getNickname)
                .isPresent();
    }

    private void validation(String name) throws ValidationException {
        if(!(name).equals(Validation.Nickname(name))) {
            throw new ValidationException(ResponseMessage.valueOfCode("Validation").getMessage());
        }
    }
}
