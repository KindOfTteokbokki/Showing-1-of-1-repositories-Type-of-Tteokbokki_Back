package halfandhalf.com.aop;

import halfandhalf.com.exception.LoginException;
import halfandhalf.domain.LG0010.dao.LG0020Dao;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.runners.Parameterized;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
@Aspect
@Slf4j
public class LoginCheckAspect {
    private final JwtTokenProvider jwtProvider;
    private final AuthTokensGenerator authTokensGenerator;
    public static Long userId;
    private final LG0020Dao lg0020Dao;

    public LoginCheckAspect(JwtTokenProvider jwtProvider, AuthTokensGenerator authTokensGenerator, LG0020Dao lg0020Dao) {
        this.jwtProvider = jwtProvider;
        this.authTokensGenerator = authTokensGenerator;
        this.lg0020Dao = lg0020Dao;
    }

    @Before("@annotation(halfandhalf.com.annotation.LoginCheckEssential)")
    public void LoginCheckEssential(JoinPoint jp) throws Throwable{
         for (Object obj : jp.getArgs()) {
             if(obj instanceof HttpServletRequest) {
                 HttpServletRequest request = (HttpServletRequest) obj;
                 if (StringUtils.hasText(request.getHeader("Authorization"))) {
                     Optional.ofNullable(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)))
                             .ifPresent(a -> {
                                 userId = a;
                                 lg0020Dao.recentlyConnection(a);
                             });
                 } else {
                     throw new LoginException("No Found AccessToken");
                 }
             }
        }
    }

    @Before("@annotation(halfandhalf.com.annotation.LoginCheckNoEssential)")
    public void LoginCheckNoEssential(JoinPoint jp) throws Throwable{
        userId= 0L;
        for (Object obj : jp.getArgs()) {
            HttpServletRequest request = (HttpServletRequest) obj;
            if(StringUtils.hasText(request.getHeader("Authorization"))){
                Optional.ofNullable(authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request)))
                        .ifPresent(a -> {
                            userId = a;
                            lg0020Dao.recentlyConnection(a);
                        });
            }
        }
    }
}
