package halfandhalf.domain.RV0010.serviceImpl;

import halfandhalf.domain.RV0010.dto.RV0010Dto;
import halfandhalf.domain.RV0010.dto.RV0011Dto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
class RV0010ServiceImplTest {
    @Autowired RV0010ServiceImpl rv0010Service;

    @Test
    @DisplayName("추천 - 페이징 처리")
    public void findRecommendByPage() throws Exception {
        //given
        RV0011Dto rv0011Dto = new RV0011Dto(0, 1);
        //when
        List<RV0010Dto> recommendByPage = rv0010Service.findRecommendByPage(rv0011Dto, 1L);
        //then
        Assertions.assertThat(recommendByPage.size()).isEqualTo(1);
    }
    
    @Test
    @DisplayName("추천 - 시퀀스로 하나 가져오기")
    public void findOneFromRecommend() throws Exception {
        //given
        RV0010Dto rv0010Dto = new RV0010Dto();
        rv0010Dto.setReview_seq(1);
        //when
        RV0010Dto oneFromRecommend = rv0010Service.findOneFromRecommend(rv0010Dto);
        //then
        Assertions.assertThat(oneFromRecommend).isNotNull();
        Assertions.assertThat(oneFromRecommend.getReview_seq()).isEqualTo(rv0010Dto.getReview_seq());
    }

    @Test
    @DisplayName("사진 없이 컨텐츠만 저장")
    public void saveRecommend1() throws Exception {
        //given

        //when

        //then

    }
    @Test
    @DisplayName("사진 있이 컨텐츠와 저장")
    public void saveRecommend2() throws Exception {
        //given
//        rv0010Service.upload();
//        rv0010Service.saveRecommend();
        //when

        //then

    }

    @Test
    @DisplayName("사진 없음 컨텐츠 없음")
    public void saveRecommend3() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("사진 있음 컨텐츠 없음")
    public void saveRecommend4() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("컨텐츠 수정")
    public void modifyRecommend1() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("사진 수정")
    public void modifyRecommend2() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("컨텐츠 사진 수정")
    public void modifyRecommend3() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("추천 삭제")
    public void deleteRecommend() throws Exception {
        //given

        //when

        //then

    }
}