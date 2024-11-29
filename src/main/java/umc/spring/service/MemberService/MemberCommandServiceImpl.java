package umc.spring.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.exception.handler.FoodCategoryHandler;
import umc.spring.apiPayload.code.exception.handler.StoreHandler;
import umc.spring.apiPayload.code.exception.handler.TempHandler;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.*;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.repository.MemberRepository.FoodCategoryRepository;
import umc.spring.repository.MemberRepository.MemberMissionRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.ReviewRepository.ReviewRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.CreateMissionDTO;
import umc.spring.web.dto.CreateReviewRequestDTO;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;

    private final FoodCategoryRepository foodCategoryRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDto request) {

        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {
            memberPrefer.setMember(newMember);
        });

        return memberRepository.save(newMember);
    }

    @Override
    public Long createReview(CreateReviewRequestDTO.createReview request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(request.getStoreId()).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        List<ReviewImage> reviewImages = request.getImgUrl().stream()
                .map(imageUrl -> ReviewImage.builder().imageUrl(imageUrl).build())
                .collect(Collectors.toList());

        Review review = Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .member(member)
                .store(store)
                .reviewImageList(reviewImages)
                .build();

        reviewImages.forEach(reviewImage -> reviewImage.setReview(review));

        return reviewRepository.save(review).getId();
    }

    @Override
    public Long createChallengingMission(CreateMissionDTO.createMission request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new TempHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new TempHandler(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission missionMission = MemberMission.builder()
                .mission(mission)
                .member(member)
                .status(MissionStatus.CHALLENGING)
                .build();

        return memberMissionRepository.save(missionMission).getId();
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return foodCategoryRepository.existsById(id);
    }
}
