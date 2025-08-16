package com.alcolella.devices.services;

import com.alcolella.devices.domain.entities.Device;
import com.alcolella.devices.domain.enums.StateEnum;

import java.util.List;
import java.util.Optional;

public interface DeviceService {

    Device createDevice(String name, String brand, StateEnum state);
    Optional<Device> getDeviceById (Long id);
    List<Device> getAllDevices();
    List<Device> getDevicesByBrand(String brand);

}
