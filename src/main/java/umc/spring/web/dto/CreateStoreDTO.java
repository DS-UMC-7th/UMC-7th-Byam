package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class CreateStoreDTO {
    @Getter
    public static class StoreDTO{
        @NotNull
        String name;
        @NotNull
        String address;
        @NotNull
        Long regionid;
    }
}
