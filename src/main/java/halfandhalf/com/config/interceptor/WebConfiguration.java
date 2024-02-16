package halfandhalf.com.config.interceptor;

import halfandhalf.com.interceptor.LoginInterceptor;
import halfandhalf.com.util.jwt.JwtUtils;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final JwtUtils jwtUtils;
    private final JwtTokenProvider jwtProvider; //JWT 유틸리티 객체 주입

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptor(jwtUtils, jwtProvider))
                .addPathPatterns("/**");
        System.out.println("WebConfiguration addInterceptors");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://www.utteok.com")
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowCredentials(true);
        System.out.println("WebConfiguration addCorsMappings");
    }
}
