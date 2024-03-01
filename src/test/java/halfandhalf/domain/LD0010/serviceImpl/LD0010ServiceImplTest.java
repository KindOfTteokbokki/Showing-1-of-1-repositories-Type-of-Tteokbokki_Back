package halfandhalf.domain.LD0010.serviceImpl;

import halfandhalf.domain.LD0010.dto.LD0010Dto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
class LD0010ServiceImplTest {
    @Autowired LD0010ServiceImpl ld0010Service;
    @Test
    @DisplayName("")
    public void findLoading() throws Exception {
        //given
        //when
        List<LD0010Dto> loading = ld0010Service.findLoading();
        //then
        assertThat(loading).isNotNull();
    }
}