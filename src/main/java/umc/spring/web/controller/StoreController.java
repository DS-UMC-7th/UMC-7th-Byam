package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.status.SuccessStatus;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.web.dto.CreateStoreDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreCommandService storeCommandService;

    @PostMapping
    public ApiResponse<?> createStore(@RequestBody CreateStoreDTO.StoreDTO storeDTO) {
        return ApiResponse.of(SuccessStatus.CREATED_STORE, storeCommandService.createStore(storeDTO));
    }
}
