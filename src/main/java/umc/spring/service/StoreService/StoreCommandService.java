package umc.spring.service.StoreService;

import umc.spring.web.dto.CreateStoreDTO;

public interface StoreCommandService {
    Long createStore(CreateStoreDTO.StoreDTO storeDTO);
}
