package halfandhalf.domain.MI0010.serviceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
public class MI0010ServiceImplTest {
    @Autowired MI0010ServiceImpl mi0010Service;

    @Test
    @DisplayName("utteok_nickname 있음")
    public void findMyInfo1() throws Exception {
        //given

        //when
        String myInfo = mi0010Service.findMyInfo(1L);
        //then
        assertThat(myInfo).isEqualTo("BigHwang");
    }
    @Test
    @DisplayName("utteok_nickname 없음")
    public void findMyInfo2() throws Exception {
        //given

        //when
        String myInfo = mi0010Service.findMyInfo(2L);
        System.out.println("myInfo : " + myInfo);
        //then
        assertThat(myInfo).isEqualTo("황태연");
    }
}