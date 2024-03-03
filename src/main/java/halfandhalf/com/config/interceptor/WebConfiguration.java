package halfandhalf.com.config.interceptor;

import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final JwtTokenProvider jwtProvider; //JWT 유틸리티 객체 주입
    private final AuthTokensGenerator authTokensGenerator;

    public WebConfiguration(JwtTokenProvider jwtProvider, AuthTokensGenerator authTokensGenerator) {
        this.jwtProvider = jwtProvider;
        this.authTokensGenerator = authTokensGenerator;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(jwtProvider, authTokensGenerator))
                .addPathPatterns("/api/myTaste")
                .addPathPatterns("/api/saveRecommend")
                .addPathPatterns("/api/haveTitle")
                .addPathPatterns("/api/doNotHaveTitle")
                .addPathPatterns("/api/myInfo")
                .addPathPatterns("/api/modifyRecommend")
                .addPathPatterns("/api/deleteRecommend")
                .addPathPatterns("/api/checkNickname")
                .addPathPatterns("/api/regiNickname");
        System.out.println("WebConfiguration addInterceptors");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://118.67.132.171")
                .allowedOrigins("http://dev.utteok.com")
                .allowedOrigins("http://101.101.209.59")
                .allowedOrigins("http://www.utteok.com")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","POST","DELETE","PUT", "OPTIONS")
                .allowCredentials(true);
        System.out.println("WebConfiguration addCorsMappings");
    }
}
