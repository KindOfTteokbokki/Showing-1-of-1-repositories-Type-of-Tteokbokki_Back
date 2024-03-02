package halfandhalf.domain.LG0010.controller;

import halfandhalf.domain.LG0010.dto.LG0020Dto;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import halfandhalf.domain.LG0010.service.LG0030Service;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59", "http://dev.utteok.com/", "http://www.utteok.com/", "http://localhost:3000"}, allowCredentials = "true")
public class LG0030Controller {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final LG0030Service lg0030Service;
    private final JwtTokenProvider jwtProvider;
    private final AuthTokensGenerator authTokensGenerator;

//    @MessageMapping("/checkNickname")
//    public void sendMessage(LG0030Dto lg0030Dto, SimpMessageHeaderAccessor accessor) {
//        lg0030Dto.setCheckNickname(lg0030Service.checkIfEnabledNickName(lg0030Dto.getNickname()));
//
//        simpMessagingTemplate.convertAndSend("/sub/checkNickname" + lg0030Dto.getChannelId(), lg0030Dto);
//    }
    
    @GetMapping("/checkNickname")
    public boolean checkNickname(@RequestParam("nickname") String nickname){
        return lg0030Service.checkIfEnabledNickName(nickname);
    }

    @PostMapping("/regiNickname")
    public void regiNickname(@RequestBody LG0020Dto lg0020Dto, HttpServletRequest request){

        String accessToken = jwtProvider.getAccessToken(request);
        Long user_id = authTokensGenerator.extractMemberId(accessToken);

        lg0020Dto.setId(user_id);
        lg0030Service.registNickname(lg0020Dto);
    }

}
