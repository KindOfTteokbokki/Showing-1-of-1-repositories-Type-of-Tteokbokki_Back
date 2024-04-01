package halfandhalf.utteokMain.member.repository;

import halfandhalf.domain.LG0010.dto.LG0020Dto;
import halfandhalf.utteokMain.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>  {

    MemberEntity findByNickname(@Param("nickname") String nickname, @Param("oAuthProvider") String oAuthProvider);

    boolean findByUtteok_nickname();

}
