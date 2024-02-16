package halfandhalf.com.config.interceptor;

import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    private final JwtTokenProvider jwtProvider; //JWT 유틸리티 객체 주입

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("JwtInterceptor preHandle");

        String uri = request.getRequestURI();
        System.out.println("uri : "+ uri);

        // 토큰 받기
        System.out.println("JwtInterceptor preHandle 실행");
        // 요청이 들어오면 실행되는 메서드
        String accessToken = jwtProvider.getAccessToken(request);
        System.out.println("Interceptor accessToken : "+ accessToken); //요청 url 로깅을 위해 가져옴
        //로깅용 URI
        String requestURI = request.getRequestURI();

        // 비회원일 때(액세스 토큰이 없을 때)
        if (!StringUtils.hasText(accessToken)) {
            System.out.println("비회원 유저입니다 URI : {}"+ requestURI);
            return true;
        } else {
            System.out.println("access 존재합니다.");
            // 액세스 토큰이 유효 시
            if ("1".equals(jwtProvider.extractSubject(accessToken))) {
                System.out.println("유효한 토큰 정보입니다. URI : {}"+ requestURI);
                return true;
            } else {
                //액세스 토큰이 유효하지 않을 시
                System.out.println("유효하지 않은 jwt 토큰입니다. URI : {}"+ requestURI);
                return false;
            }
        }
    }

}
