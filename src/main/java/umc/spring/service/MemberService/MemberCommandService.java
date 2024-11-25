package umc.spring.service.MemberService;

import umc.spring.domain.Member;
import umc.spring.web.dto.CreateReviewRequestDTO;
import umc.spring.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);

    Long createReview(CreateReviewRequestDTO.createReview request);
}
