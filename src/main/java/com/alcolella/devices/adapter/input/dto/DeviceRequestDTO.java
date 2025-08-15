package com.alcolella.devices.adapter.input.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class DeviceRequestDTO {

    private String name;
    private String brand;
    private String state;

    public DeviceRequestDTO() {
    }

    public DeviceRequestDTO(String name, String brand, String state) {
        this.name = name;
        this.brand = brand;
        this.state = state;
    }



}
