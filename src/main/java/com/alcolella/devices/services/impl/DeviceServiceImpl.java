package com.alcolella.devices.services.impl;

import com.alcolella.devices.domain.entities.Device;
import com.alcolella.devices.domain.enums.StateEnum;
import com.alcolella.devices.repositories.DeviceRepository;
import com.alcolella.devices.services.DeviceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device createDevice(String name, String brand, StateEnum state) {
        return deviceRepository.save( buildDevice(name, brand, state));
    }

    @Override
    public Optional<Device> getDeviceById(Long id) {
        return deviceRepository.findById(id);
    }

    @Override
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    private Device buildDevice(String name, String brand, StateEnum state) {
        return Device.builder()
                .name(name)
                .brand(brand)
                .state(state)
                .build();
    }

}
