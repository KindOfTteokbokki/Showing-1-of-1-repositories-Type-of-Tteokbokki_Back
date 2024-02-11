package halfandhalf.com.pagenation;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.List;

// https://www.devjoon.com/41 참고

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
//스크롤 페이지네이션을 편리하게 구현하기 위한 클래스입니다
public class ScrollPaginationCollection<T> {

    // 현재 스크롤의 요소 + 다음 스크롤의 요소 1개 (다음 스크롤이 있는지 확인을 위한)
    private final List<T> itemsWithNextCursor;
    // 스크롤 1회에 조회할 데이터의 개수입니다.
    private final int countPerScroll;

    public static <T> ScrollPaginationCollection<T> of(List<T> itemsWithNextCursor, int size) {
        return new ScrollPaginationCollection<>(itemsWithNextCursor, size);
    }

    // 현재 스크롤이 마지막 스크롤인지 확인하기 위한 메소드입니다.
    // 쿼리로 데이터를 조회한 결과 countPerScroll의 숫자 이하로 조회되면 마지막 스크롤이라고 판단합니다.
    public boolean isLastScroll() {
        return this.itemsWithNextCursor.size() <= countPerScroll;
    }

    // 마지막 스크롤일 경우 itemsWithNextCursor를 return 하고
    // 마지막 스크롤이 아닐 경우 다음 스크롤의 데이터 1개를 제외하고 return 합니다.
    public List<T> getCurrentScrollItems() {
        if (isLastScroll()) {
            //마지막 스크롤
            return this.itemsWithNextCursor;
        }
        //마지막 스크롤이 아닐 경우
        return this.itemsWithNextCursor.subList(0, countPerScroll);
    }

    // 현재 스크롤의 데이터 중 마지막 데이터를 cursor로 사용하고 이를 return 합니다.
    public T getNextCursor() {
        return itemsWithNextCursor.get(countPerScroll - 1);
    }

}
