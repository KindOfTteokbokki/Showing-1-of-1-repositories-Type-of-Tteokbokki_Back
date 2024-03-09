package halfandhalf.com.aop;

import halfandhalf.com.config.ResponseMessage;
import halfandhalf.com.exception.LoginException;
import halfandhalf.domain.LD0010.dao.LD0010Dao;
import halfandhalf.domain.LG0010.dao.LG0020Dao;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class LoginCheckAspect {
    private final JwtTokenProvider jwtProvider;
    private final AuthTokensGenerator authTokensGenerator;
    private final LG0020Dao lD0020Dao;

    public LoginCheckAspect(JwtTokenProvider jwtProvider, AuthTokensGenerator authTokensGenerator, LG0020Dao lD0020Dao) {
        this.jwtProvider = jwtProvider;
        this.authTokensGenerator = authTokensGenerator;
        this.lD0020Dao = lD0020Dao;
    }

    @Before("@annotation(halfandhalf.com.annotation.LoginCheckEssential)")
    public void LoginCheckEssential(JoinPoint jp) throws Throwable{
         for (Object obj : jp.getArgs()) {
             if(obj instanceof HttpServletRequest) {
                 HttpServletRequest request = (HttpServletRequest) obj;
                 if (StringUtils.hasText(request.getHeader("Authorization"))) {
                     Long id = authTokensGenerator.extractMemberId(jwtProvider.getAccessToken(request));
                     String checkId = jwtProvider.extractSubject(jwtProvider.getAccessToken(request));
//                     jwtProvider.extractSubject(jwtProvider.getAccessToken(request));
                     if(id != Integer.parseInt(checkId)) {
                         throw new LoginException(ResponseMessage.valueOfCode("Validation").getMessage());
                     } else {
                         lD0020Dao.recentlyConnection(id);
                     }
                 } else {
//                     throw new LoginException("No Found AccessToken");
                     throw new LoginException(ResponseMessage.valueOfCode("Unauthorized").getMessage());
                 }
             }
        }
    }
}
