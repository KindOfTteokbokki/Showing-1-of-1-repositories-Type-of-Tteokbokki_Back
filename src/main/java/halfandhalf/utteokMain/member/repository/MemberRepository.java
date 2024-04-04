package halfandhalf.utteokMain.member.repository;

import halfandhalf.utteokMain.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>  {
    MemberEntity findByNicknameAndOauthProvider(@Param("nickname") String nickname, @Param("oAuthProvider") String oAuthProvider);

    MemberEntity findByUtteokNickname(@Param("utteok_nickname") String nickname);

    MemberEntity findUtteokNicknameById(@Param("id") Long id);
}
