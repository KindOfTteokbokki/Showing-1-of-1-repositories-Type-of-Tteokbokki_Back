package halfandhalf.com.config.websocker;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    //엔드포인트를 등록하기 위해 registerStompEndpoints method를 overide 한다.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://118.67.132.171")
                .setAllowedOrigins("http://101.101.209.59")
                .setAllowedOrigins("http://dev.utteok.com")
                .setAllowedOrigins("http://www.utteok.com");
    }
    //Message broker를 설정하기 위해 configureMessageBroker method를 overide 한다.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/sub");    // 메시지 발신
        registry.setApplicationDestinationPrefixes("/pub");     // 메시지 수신
    }
}