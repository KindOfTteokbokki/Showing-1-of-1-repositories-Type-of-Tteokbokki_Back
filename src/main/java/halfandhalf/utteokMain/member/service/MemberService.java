package halfandhalf.utteokMain.member.service;


import halfandhalf.com.util.Validation;
import halfandhalf.utteokMain.member.entity.MemberEntity;
import halfandhalf.utteokMain.member.oauth.client.RequestOAuthInfoService;
import halfandhalf.utteokMain.member.oauth.jwt.AuthTokens;
import halfandhalf.utteokMain.member.oauth.jwt.AuthTokensGenerator;
import halfandhalf.utteokMain.member.oauth.param.OAuthLoginParams;
import halfandhalf.utteokMain.member.oauth.response.OAuthInfoResponse;
import halfandhalf.utteokMain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public MemberService(MemberRepository memberRepository, AuthTokensGenerator authTokensGenerator, RequestOAuthInfoService requestOAuthInfoService) {
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
        return Optional.ofNullable(
                    memberRepository.findByNicknameAndOAuthProvider(Validation.Nickname(oAuthInfoResponse.getNickname())
                        , String.valueOf(oAuthInfoResponse.getOAuthProvider())))
                .map(MemberEntity::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    @Transactional
    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        MemberEntity member = MemberEntity.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(Validation.Nickname(oAuthInfoResponse.getNickname()))
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        memberRepository.save(member);
        return memberRepository.findByNicknameAndOAuthProvider(member.getNickname(), String.valueOf(member.getOAuthProvider())).getId();
    }

}
