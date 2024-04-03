package halfandhalf.domain.ST0010.serviceImpl;

import halfandhalf.domain.ST0010.dao.ST0010Dao;
import halfandhalf.domain.ST0010.dto.ST0010Dto;
import halfandhalf.domain.ST0010.dto.ST0011Dto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
public class ST0010ServiceImplTest {
    @Autowired
    private ST0010ServiceImpl st0010ServiceImpl;
    @Autowired
    private ST0010Dao sT0010Dao;

    @Test
    @DisplayName("내입맛 하나 가져오기")
    public void findOneFromMyTaste() throws Exception {
        //given
        ST0010Dto st0010Dto = new ST0010Dto("ch102", "ch200", "ch301", "ch401", "ch501", "ch601", "ch700");
        //when
        ST0010Dto store = st0010ServiceImpl.findStore(st0010Dto, 1L);
        ST0010Dto oneFromMyTaste = st0010ServiceImpl.findOneFromMyTaste(store);
        //then
        Assertions.assertThat(oneFromMyTaste).isNotNull();
    }
    @Test
    @DisplayName("아무것도 선택 없음, 가게 정보 노출, count 안됨")
    public void findStore1() throws Exception {
        //given
        ST0010Dto st0010Dto = new ST0010Dto("ch100", "ch200", "ch300", "ch400", "ch500", "ch600", "ch700");
        //when
        ST0010Dto store = st0010ServiceImpl.findStore(st0010Dto, 1L);
        ST0011Dto myTasteByIdSeq2 = sT0010Dao.findMyTasteByIdSeq(new ST0011Dto(1L, store.getStore_seq()));
        ST0011Dto result = null;
        for(ST0011Dto dto : sT0010Dao.findMyTasteById(new ST0011Dto(1L, 0))) {
            if (dto.getStore_seq() == myTasteByIdSeq2.getStore_seq()) {
                result = dto;
            }
        }
        //then
        Assertions.assertThat(store).isNotNull();
        Assertions.assertThat(result.getMenu_count()).isEqualTo(myTasteByIdSeq2.getMenu_count());
    }
    @Test
    @DisplayName("선택은 있지만 없는 데이터, 가게 정보 노출, count 됨")
    public void findStore2() throws Exception {
        //given
        ST0010Dto st0010Dto = new ST0010Dto("ch100", "ch200", "ch300", "ch402", "ch500", "ch600", "ch703");
        //when
        ST0010Dto store = st0010ServiceImpl.findStore(st0010Dto, 1L);
        ST0011Dto myTasteByIdSeq2 = sT0010Dao.findMyTasteByIdSeq(new ST0011Dto(1L, store.getStore_seq()));
        ST0011Dto result = null;
        for(ST0011Dto dto : sT0010Dao.findMyTasteById(new ST0011Dto(1L, 0))) {
            if (dto.getStore_seq() == myTasteByIdSeq2.getStore_seq()) {
                result = dto;
            }
        }
        //then
        Assertions.assertThat(store).isNotNull();
        Assertions.assertThat(result.getMenu_count()).isEqualTo(myTasteByIdSeq2.getMenu_count());
    }
    @Test
    @DisplayName("선택이 있고 있는 데이터, 가게 정보 노출, count 됨")
    public void findStore3() throws Exception {
        //given
        ST0010Dto st0010Dto = new ST0010Dto("ch102", "ch200", "ch301", "ch401", "ch501", "ch601", "ch700");
        //when
        ST0010Dto store = st0010ServiceImpl.findStore(st0010Dto, 1L);
        ST0011Dto myTasteByIdSeq2 = sT0010Dao.findMyTasteByIdSeq(new ST0011Dto(1L, store.getStore_seq()));
        ST0011Dto result = null;
        for(ST0011Dto dto : sT0010Dao.findMyTasteById(new ST0011Dto(1L, 0))) {
            if (dto.getStore_seq() == myTasteByIdSeq2.getStore_seq()) {
                result = dto;
            }
        }
        //then
        Assertions.assertThat(store).isNotNull();
        Assertions.assertThat(result.getMenu_count() +1).isEqualTo(myTasteByIdSeq2.getMenu_count() +1);
    }
    @Test
    @DisplayName("")
    public void findMyTasteByCount() throws Exception {
        //given

        //when
        List<ST0010Dto> myTasteByCount = st0010ServiceImpl.findMyTasteByCount(1L);
        //then
        Assertions.assertThat(myTasteByCount.size()).isEqualTo(4);
    }
}