package com.alcolella.devices.adapter.input.dto;

import com.alcolella.devices.domain.enums.StateEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeviceRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Brand cannot be blank")
    private String brand;
    @NotNull(message = "State cannot be null")
    private StateEnum state;

    public DeviceRequestDTO() {
    }

    public DeviceRequestDTO(String name, String brand, StateEnum state) {
        this.name = name;
        this.brand = brand;
        this.state = state;
    }

}
