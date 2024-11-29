package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.SuccessStatus;
import umc.spring.converter.MemberConverter;
import umc.spring.domain.Member;
import umc.spring.service.MemberService.MemberCommandService;
import umc.spring.web.dto.CreateMissionDTO;
import umc.spring.web.dto.CreateReviewRequestDTO;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/review")
    public ApiResponse<?> createReview(@RequestBody @Valid CreateReviewRequestDTO.createReview request){
        Long result = memberCommandService.createReview(request);
        return ApiResponse.of(SuccessStatus.CREATED_REVIEW,result);
    }

    @PostMapping("/mission")
    public ApiResponse<?> createChallengingMission(
            @Valid @RequestBody CreateMissionDTO.createMission request){
        return ApiResponse.of(SuccessStatus.CREATED_MISSION, memberCommandService.createChallengingMission(request));
    }
}