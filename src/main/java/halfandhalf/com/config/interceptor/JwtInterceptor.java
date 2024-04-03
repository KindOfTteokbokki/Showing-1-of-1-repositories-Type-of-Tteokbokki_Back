package halfandhalf.com.config.interceptor;

import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator_;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);

    private final JwtTokenProvider_ jwtProvider; //JWT 유틸리티 객체 주입
    private final AuthTokensGenerator_ authTokensGenerator;

    public JwtInterceptor(JwtTokenProvider_ jwtProvider, AuthTokensGenerator_ authTokensGenerator) {
        this.jwtProvider = jwtProvider;
        this.authTokensGenerator = authTokensGenerator;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("JwtInterceptor preHandle");

        String requestURI = request.getRequestURI();
        System.out.println("JwtInterceptor uri : "+ requestURI);

        // 요청이 들어오면 실행되는 메서드
        String accessToken = jwtProvider.getAccessToken(request);
        System.out.println("request.getHeader(Authorization) : "+ request.getHeader("Authorization")); //요청 url 로깅을 위해 가져옴
        System.out.println("JwtInterceptor Interceptor accessToken : "+ accessToken); //요청 url 로깅을 위해 가져옴

        // 비회원일 때(액세스 토큰이 없을 때)
        if (!StringUtils.hasText(accessToken)) {
            System.out.println("비회원 유저입니다 URI : {}"+ requestURI);
            return false;
        } else {
            System.out.println("access 존재합니다.");
            Optional<Long> user_id = Optional.ofNullable(authTokensGenerator.extractMemberId(accessToken));
            // 액세스 토큰이 유효 시
            if (user_id.isPresent() && user_id.get().intValue() == Integer.parseInt(jwtProvider.extractSubject(accessToken))) {
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
