package oauth;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class jasypy {

    @Test
    void jasypt(){
        String jwt = "";
        String kakao_client = "";
        String naver_secret = "";
        String naver_client = "";

        String encryptjwt = jasyptEncrypt(jwt);
        String encryptkakao_client = jasyptEncrypt(kakao_client);
        String encryptnaver_secret = jasyptEncrypt(naver_secret);
        String encryptnaver_client = jasyptEncrypt(naver_client);

        System.out.println("encryptjwt : " + encryptjwt);
        System.out.println("encryptkakao_client : " + encryptkakao_client);
        System.out.println("encryptnaver_secret : " + encryptnaver_secret);
        System.out.println("encryptnaver_client : " + encryptnaver_client);

        Assertions.assertThat(jwt).isEqualTo(jasyptDecryt(encryptjwt));
    }

    private String jasyptEncrypt(String input) {
        String key = "";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.encrypt(input);
    }

    private String jasyptDecryt(String input){
        String key = "";
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword(key);
        return encryptor.decrypt(input);
    }
}
