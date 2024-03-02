package halfandhalf.com.annotation;

import halfandhalf.com.exception.LoginException;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@Aspect
public class LoginCheckAnnotation {
    private final JwtTokenProvider jwtProvider;
    private final AuthTokensGenerator authTokensGenerator;

    public LoginCheckAnnotation(JwtTokenProvider jwtProvider, AuthTokensGenerator authTokensGenerator) {
        this.jwtProvider = jwtProvider;
        this.authTokensGenerator = authTokensGenerator;
    }

    @Before("@annotation(halfandhalf.com.annotation.LoginCheck)")
    public void LoginCheck(JoinPoint jp) throws Throwable{
        for (Object obj : jp.getArgs()) {
            if (obj instanceof HttpServletRequest || obj instanceof MultipartHttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) obj;
                Optional.of(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)))
                        .orElseThrow(()->new LoginException("No found AccessToken"));
            }
        }

    }
}
