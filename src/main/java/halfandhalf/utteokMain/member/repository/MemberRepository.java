package halfandhalf.utteokMain.member.repository;

import halfandhalf.utteokMain.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>  {
    MemberEntity findByNicknameAndOAuthProvider(@Param("nickname") String nickname, @Param("oAuthProvider") String oAuthProvider);

    MemberEntity findByUtteok_nickname(@Param("utteok_nickname") String nickname);

    MemberEntity findUtteok_nicknameById(@Param("id") Long id);
}
