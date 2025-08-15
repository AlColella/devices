package com.alcolella.devices.adapter.input.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeviceResponseDTO {

    private Long id;
    private String name;
    private String brand;
    private String state;
    private String creationTime;

    public DeviceResponseDTO() {
    }

    public DeviceResponseDTO(Long id, String name, String brand, String state, String creationTime) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.state = state;
        this.creationTime = creationTime;
    }

}
