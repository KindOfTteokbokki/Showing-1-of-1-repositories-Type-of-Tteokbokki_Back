package halfandhalf.com.pagenation;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//클라이언트에게 전달할 dto인 GetFeedsResponse 클래스입니다.
public class GetFeedsResponse {

//    private static final long LAST_CURSOR = -1L;
////    long LAST_CURSOR = -1L: 다음 스크롤이 존재하지 않을 경우 nextCursor 에 넣어주기 위한 값입니다.
////    nextCursor = -1L일 경우 해당 스크롤이 마지막 스크롤임을 뜻합니다.
//
//    private List<FeedsInfoResponse> contents = new ArrayList<>();
//    //List<FeedsInfoResponse> contents: 클라이언트에게 최종적으로 전달될 데이터들입니다.FeedsInfoResponse 는 서비스 로직에서 조회한 Feed 를 가공한 형태입니다.
//    private long totalElements;
////    long totalElements: 조회 가능한 데이터의 총 개수입니다.
//    private long nextCursor;
////    : 다음 스크롤에서 사용할 커서의 값입니다.
//
//    private GetFeedsResponse(List<FeedsInfoResponse> contents, long totalElements, long nextCursor) {
//        this.contents = contents;
//        this.totalElements = totalElements;
//        this.nextCursor = nextCursor;
//    }
//
////    GetFeedsResponse of(ScrollPaginationCollection<Feed> feedsScroll, FeedImageCollection feedImages, long totalElements):
////    서비스 로직에서는 해당 메소드를 사용해서 조회한 데이터를 클라이언트에게 전달할 데이터로 가공합니다.
////    ScrollPaginationCollection 클래스의 isLastScroll 메소드를 사용해서 해당 스크롤이 마지막 스크롤인지 확인합니다.
////    이후에 마지막 스크롤인지 여부에 따라 newLastScroll 또는 newScrollHasNext 메소드를 호출합니다.
//    public static GetFeedsResponse of(ScrollPaginationCollection<Feed> feedsScroll, FeedImageCollection feedImages, long totalElements) {
//        if (feedsScroll.isLastScroll()) {
//            return GetFeedsResponse.newLastScroll(feedsScroll.getCurrentScrollItems(), feedImages, totalElements);
//        }
//        return GetFeedsResponse.newScrollHasNext(feedsScroll.getCurrentScrollItems(), feedImages, totalElements, feedsScroll.getNextCursor().getId());
//    }
//
////    GetFeedsResponse newLastScroll(List<Feed> feedsScroll, FeedImageCollection feedImages, long totalElements):
////    다음 스크롤이 존재하지 않을 경우 nextCursor 에 1L을 담아서 객체를 생성하기 위한 메소드입니다.
//    private static GetFeedsResponse newLastScroll(List<Feed> feedsScroll, FeedImageCollection feedImages, long totalElements) {
//        return newScrollHasNext(feedsScroll, feedImages, totalElements, LAST_CURSOR);
//    }
//
////    GetFeedsResponse newScrollHasNext(List<Feed> feedsScroll, FeedImageCollection feedImages, long totalElements, long nextCursor):
////    다음 스크롤이 존재하는 경우 nextCursor 에 다음 커서 값을 담아서 객체를 생성하기 위한 메소드입니다.
//    private static GetFeedsResponse newScrollHasNext(List<Feed> feedsScroll, FeedImageCollection feedImages, long totalElements, long nextCursor) {
//        return new GetFeedsResponse(getContents(feedsScroll, feedImages), totalElements, nextCursor);
//    }
//
////    List<FeedsInfoResponse> getContents(List<Feed> feedsScroll, FeedImageCollection feedImages):
////    contents 로 전달할 데이터로 가공하기 위한 메소드입니다.
//    private static List<FeedsInfoResponse> getContents(List<Feed> feedsScroll, FeedImageCollection feedImages) {
//        return feedsScroll.stream()
//                .map(feed -> FeedsInfoResponse.of(feed, feedImages.getImagesByFeedId(feed.getId())))
//                .collect(Collectors.toList());
//    }
}
