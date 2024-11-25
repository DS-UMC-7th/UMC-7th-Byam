package umc.spring.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.spring.validation.annotation.ExistStore;

import java.util.List;

public class CreateReviewRequestDTO {
    @Getter
    public static class createReview{
        @NotNull
        Long memberId; //Todo : 로그인 구현 후 삭제

        @NotBlank
        String body;

        @Max(value = 5, message = "5.0 이하로만 작성이 가능합니다.")
        Float score;

        List<String> imgUrl;

        @ExistStore
        Long storeId;
    }
}
