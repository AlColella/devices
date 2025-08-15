package com.alcolella.devices.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DeviceDTO {

    private Long id;
    private String name;
    private String brand;
    private String state;
    private String creationTime;

    public DeviceDTO() {
    }

    public DeviceDTO(Long id, String name, String brand, String state, String creationTime) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.state = state;
        this.creationTime = creationTime;
    }

}
