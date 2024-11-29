package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.repository.MemberRepository.MemberMissionRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.validation.annotation.ExistChallengingMission;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChallengingMissionExistValidator implements ConstraintValidator<ExistChallengingMission, Long> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(ExistChallengingMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        Long memberId = 1L; // Todo : 하드 코딩된 사용자. 추후 수정.

        // `CHALLENGING` 상태의 도전이 존재하면 false
        return !memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(memberId, missionId, MissionStatus.CHALLENGING);
    }
}
