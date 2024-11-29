package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.exception.GeneralException;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository.RegionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.web.dto.CreateStoreDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    public Long createStore(CreateStoreDTO.StoreDTO storeDTO) {
        Region region = regionRepository.findById(storeDTO.getRegionid())
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        Store store = Store.builder()
                .name(storeDTO.getName())
                .address(storeDTO.getAddress())
                .region(region)
                .build();
        return storeRepository.save(store).getId();
    }
}
