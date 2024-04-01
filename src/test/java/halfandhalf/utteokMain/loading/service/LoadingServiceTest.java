package halfandhalf.utteokMain.loading.service;

import halfandhalf.utteokMain.loading.dto.LoadingDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
class LoadingServiceTest {
    @Autowired LoadingService loadingService;

    @Test
    @DisplayName("")
    public void findOne() throws Exception {
        //given
        LoadingDto top1Random = loadingService.findTop1Random();
        //when
        System.out.println("top1Random = " + top1Random.getPhrases());
        //then
        assertThat(top1Random.getPhrases()).isNotNull();
    }
}