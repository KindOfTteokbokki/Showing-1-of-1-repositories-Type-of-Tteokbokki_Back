package halfandhalf.domain.LG0010.controller;

import halfandhalf.domain.LG0010.dto.LG0030Dto;
import halfandhalf.domain.LG0010.service.LG0030Service;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://118.67.132.171", "http://101.101.209.59"}, allowCredentials = "true")
public class LG0030Controller {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final LG0030Service lg0030Service;

    @MessageMapping("/checkNickname")
    public void sendMessage(LG0030Dto lg0030Dto, SimpMessageHeaderAccessor accessor) {
        lg0030Dto.setCheckNickname(lg0030Service.checkIfEnabledNickName(lg0030Dto.getNickname()));

        simpMessagingTemplate.convertAndSend("/sub/checkNickname" + lg0030Dto.getChannelId(), lg0030Dto);
    }
}
