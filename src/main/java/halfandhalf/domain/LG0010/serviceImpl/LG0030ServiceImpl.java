package halfandhalf.domain.LG0010.serviceImpl;

import halfandhalf.domain.LG0010.dao.LG0030Dao;
import halfandhalf.domain.LG0010.dto.LG0020Dto;
import halfandhalf.domain.LG0010.dto.LG0030Dto;
import halfandhalf.domain.LG0010.service.LG0030Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LG0030ServiceImpl implements LG0030Service {
    private final LG0030Dao memberRepository;

    public LG0030ServiceImpl(LG0030Dao memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean checkIfEnabledNickName(String nickname){
        return Optional.ofNullable(memberRepository.checkIfEnabledNickName(nickname))
                .map(LG0030Dto::getNickname)
                .isPresent();
    }

    @Override
    public void registNickname(LG0020Dto lg0020Dto) {
        memberRepository.registNickname(lg0020Dto);
    }
}