package halfandhalf.com.pagenation;

import org.apache.catalina.User;

//실제 서비스 로직에서 ScrollPaginationCollection<T> 클래스를 사용한 예시입니다
public class example {
    public GetFeedsResponse getFeeds(String userEmail, Long roomId, int size, Long lastFeedId) {
        // int size, Long lastFeedId에 집중
        // int size: 스크롤 1회에 조회할 데이터의 개수
        // Long lastFeedId: 커서로 사용하는 데이터 식별자입니다.
        // id 내림차순으로 데이터를 조회하기 때문에 다음 스크롤은 lastFeedId 보다 작은 id의 데이터만 확인합니다.

        // 다음은 Page<T> 인터페이스, Pageable 인터페이스, PageRequest 클래스에 대한 이해가 필요합니다.
        // Page<T>인터페이스는 페이지 정보를 담습니다.
        // Pageable 인터페이스는 페이지 처리에 필요한 정보를 담고 있습니다.
        // PageRequest 클래스는 Pageable 의 정보가 담겨 객체화 된 클래스입니다.
        // JpaRepository 가 상속된 인터페이스의 파라미터로 PageRequest 를 전달하면Page<T>를 return 합니다.
//        User user = FeedServiceUtils.findUserByEmail(userRepository, userEmail);
//        Room room = FeedServiceUtils.findRoomByRoomId(roomRepository, roomId);
//
//        PageRequest pageRequest = PageRequest.of(0, size + 1);
//        //PageRequest pageRequest = PageRequest.of(0, size + 1):PageRequest 객체의 of 메소드는 인자로 조회할 page 와 한 페이지당 조회할 데이터의 개수 size 를 받습니다.
//        //커서 기반 페이지네이션이기 때문에 항상 lastFeedId 이후의 id 로만 조회하므로 첫번째 페이지의 정보를 받으면 됩니다.
//        //size 에는 다음 스크롤이 있는지 판단하기 위해 다음 스크롤의 요소 1개를 포함한 size + 1을 입력합니다.
//        Page<Feed> page = feedRepository.findAllByRoomAndIdLessThanOrderByIdDesc(room, lastFeedId, pageRequest);
//        //Page<Feed> page = feedRepository.findAllByRoomAndIdLessThanOrderByIdDesc(room, lastFeedId, pageRequest):
//        //   JpaRepository 를 상속한 feedRepository 에 파라미터로 커서로 사용하는 lastFeedId 와 PageRequest 를 담아서 데이터를 조회합니다.
//        List<Feed> feeds = page.getContent();
//        //List<Feed> feeds = page.getContent(): Page<T>가 제공하는 getContent 메소드로 조회한 데이터를 가져옵니다.
//
//        ScrollPaginationCollection<Feed> feedsCursor = ScrollPaginationCollection.of(feeds, size);
////        ScrollPaginationCollection<Feed> feedsCursor = ScrollPaginationCollection.of(feeds, size):
////        위에서 소개한ScrollPaginationCollection<T> 클래스의 of 메소드의 인자로 ScrollPaginationCollection 객체를 생성합니다.
//        GetFeedsResponse response = GetFeedsResponse.of(feedsCursor, FeedImageCollection.of(feeds, feedImageRepository), feedRepository.countAllByRoom(room));
////        GetFeedsResponse response = GetFeedsResponse.of(feedsCursor, FeedImageCollection.of(feeds, feedImageRepository), feedRepository.countAllByRoom(room)):
////        클라이언트측에 전달할 Response 형식으로 변환해준 뒤 이를 return 합니다.

//        return response;
        return null;
    }
}
