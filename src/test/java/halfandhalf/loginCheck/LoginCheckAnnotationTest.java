package halfandhalf.loginCheck;

import halfandhalf.domain.CB0010.serviceImpl.CB0010ServiceImpl;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator_;
import halfandhalf.domain.LG0010.oauth.jwt.JwtTokenProvider_;
import halfandhalf.domain.MI0010.controller.MI0010Controller;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
public class LoginCheckAnnotationTest {

    @Autowired
    CB0010ServiceImpl cb0010Service;
    @Autowired
    MI0010Controller controller;
    @Autowired
    JwtTokenProvider_ jwtProvider;
    @Autowired
    AuthTokensGenerator_ authTokensGenerator;

    @Test
    @DisplayName("")
    public void EventImpl() throws Exception {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMTEyIiwiZXhwIjoxNzA5NDY4MjI3fQ.VzEIAkSb_WbyY0HATp4ZHb2m5u-xryoxIrz_8CKo4XpdZ_1ZPSn-PmwXRohgwYgILHEBF0gGWVdwNGbEZFufAw");


        ResponseEntity<?> responseEntity = controller.myInfo(mockRequest);
        System.out.println(responseEntity);

    }
}
