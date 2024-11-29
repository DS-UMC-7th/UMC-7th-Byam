package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistChallengingMission;

public class CreateMissionDTO {
    @Getter
    public static class createMission {
        @NotNull
        Long memberId; //Todo : 로그인 구현 후 삭제

        @ExistChallengingMission
        Long missionId;
    }
}
