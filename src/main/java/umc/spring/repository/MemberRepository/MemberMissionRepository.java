package umc.spring.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
@Repository
public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    @Query("SELECT COUNT(mm) > 0 FROM MemberMission mm " +
            "WHERE mm.member.id = :memberId AND mm.mission.id = :missionId AND mm.status = :status")
    boolean existsByMemberIdAndMissionIdAndStatus(
            @Param("memberId") Long memberId,
            @Param("missionId") Long missionId,
            @Param("status") MissionStatus status);
}
