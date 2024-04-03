package halfandhalf.domain.LG0010.serviceImpl;

import halfandhalf.com.util.Validation;
import halfandhalf.domain.LG0010.dao.LG0020Dao;
import halfandhalf.domain.LG0010.dto.LG0020Dto;
import halfandhalf.domain.LG0010.oauth.client.RequestOAuthInfoService_;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokens;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator_;
import halfandhalf.domain.LG0010.oauth.param.OAuthLoginParams;
import halfandhalf.domain.LG0010.oauth.response.OAuthInfoResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LG0020ServiceImpl {
    private final LG0020Dao memberRepository;
    private final AuthTokensGenerator_ authTokensGenerator;
    private final RequestOAuthInfoService_ requestOAuthInfoService;

    public LG0020ServiceImpl(LG0020Dao memberRepository, AuthTokensGenerator_ authTokensGenerator, RequestOAuthInfoService_ requestOAuthInfoService) {
        this.memberRepository = memberRepository;
        this.authTokensGenerator = authTokensGenerator;
        this.requestOAuthInfoService = requestOAuthInfoService;
    }

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return Optional.ofNullable(memberRepository.findByNickname(Validation.Nickname(oAuthInfoResponse.getNickname())
                        , String.valueOf(oAuthInfoResponse.getOAuthProvider())))
                .map(LG0020Dto::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        LG0020Dto member = LG0020Dto.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(Validation.Nickname(oAuthInfoResponse.getNickname()))
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        memberRepository.save(member);
        return memberRepository.findByNickname(member.getNickname(), String.valueOf(member.getOAuthProvider())).getId();
    }

    public LG0020Dto findById(Long id){
        return memberRepository.findById(id);
    }
}