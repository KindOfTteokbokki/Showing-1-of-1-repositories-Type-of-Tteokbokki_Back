package oauth;

import halfandhalf.domain.LG0010.dto.LG0020Dto;
import halfandhalf.domain.LG0010.oauth.jwt.AuthTokensGenerator;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class login {

    @Test
    void nullTest() {
        String testString = null;
        Optional.of(testString)
                .orElseGet(() -> "testMethod()");
    }

    @Test
    void login() {
//        AuthTokensGenerator atg = new AuthTokensGenerator();
//        AuthTokensGenerator.extractMemberId
    }

    void testMethod() {
        System.out.println("SUCCESS");
    }
}
