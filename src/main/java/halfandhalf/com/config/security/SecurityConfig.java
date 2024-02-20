package halfandhalf.com.config.security;


import halfandhalf.com.config.AppConfig;
import halfandhalf.com.util.jwt.JwtUtils;
import halfandhalf.domain.LG0010.serviceImpl.LG0010ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration // 해당 클래스를 Spring Configuration으로 등록
@EnableWebSecurity // Spring Security를 활성화
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) // 메소드 수준에서의 보안을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtUtils jwtUtils;
    private final LG0010ServiceImpl loginService;
    private final AppConfig appConfig;

//    @Autowired
//    public SecurityConfig(@Lazy JwtUtils jwtUtils, LG0010ServiceImpl loginService, AppConfig appConfig) {
//        this.jwtUtils = jwtUtils;
//        this.loginService = loginService;
//        this.appConfig = appConfig;
//    }

    @Override // HttpSecurity를 사용하여 Web Security 설정을 오버라이드한다.
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // CSRF(사이트 간 요청 위조) 공격 방어를 비활성화
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // 세션을 생성하지 않으며, STATELESS로 설정하여, 서버가 상태를 저장하지 않게합니다.
            .and()
            .authorizeRequests() // HttpServletRequest에 따라 접근을 제한
            .antMatchers("/**").permitAll() // 모든 경로에 대해 접근을 허용
    //                .antMatchers(
    //                        "/api/login"
    //                ).permitAll()
            .anyRequest().authenticated(); // 그 외의 모든 요청은 인증을 요구
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(appConfig.passwordEncoder());
    }

    @Bean // AuthenticationManager Bean을 생성하여 Spring Context에 등록
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean(); // 기본 AuthenticationManager Bean을 반환
    }
}