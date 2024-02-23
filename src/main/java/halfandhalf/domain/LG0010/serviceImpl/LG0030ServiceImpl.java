package halfandhalf.domain.LG0010.serviceImpl;

import halfandhalf.domain.LG0010.dao.LG0030Dao;
import halfandhalf.domain.LG0010.dto.LG0030Dto;
import halfandhalf.domain.LG0010.service.LG0030Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LG0030ServiceImpl implements LG0030Service {
    private final LG0030Dao memberRepository;
    public boolean checkIfEnabledNickName(String nickname){
        return Optional.ofNullable(memberRepository.checkIfEnabledNickName(nickname))
                .map(LG0030Dto::getNickname)
                .isPresent();
    }
}