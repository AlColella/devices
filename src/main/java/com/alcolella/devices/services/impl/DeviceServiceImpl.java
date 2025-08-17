package com.alcolella.devices.services.impl;

import com.alcolella.devices.domain.entities.Device;
import com.alcolella.devices.domain.enums.StateEnum;
import com.alcolella.devices.exceptions.DeviceInUseException;
import com.alcolella.devices.exceptions.DeviceNotFoundException;
import com.alcolella.devices.exceptions.DeviceUpdateException;
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

    @Override
    public List<Device> getDevicesByBrand(String brand) {
        return deviceRepository.findAllByBrand(brand);
    }

    @Override
    public List<Device> getDevicesByState(StateEnum state) {
        return deviceRepository.findAllByState(state);
    }

    @Override
    public void deleteDevice(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found with id: " + id));

        if (device.getState() == StateEnum.IN_USE) {
            throw new DeviceInUseException("Device in use cannot be deleted");
        }
        deviceRepository.deleteById(id);
    }

    @Override
    public Device updateDevice(Long id, String name, String brand, StateEnum state) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found with id: " + id));
        if (device.getState() == StateEnum.IN_USE) {
           if(!device.getName().equals(name) ||
              !device.getBrand().equals(brand)) {
               throw new DeviceInUseException("Cannot update name or brand of a device in use");
           }
        }
        if(name != null && !name.isEmpty() && !name.equals(device.getName())) {
            device.setName(name);
        }
        if(brand != null && !brand.isEmpty() && !brand.equals(device.getBrand())) {
            device.setBrand(brand);
        }
        if(state != null && state != device.getState()) {
           device.setState(state);
        }
        try {
            return deviceRepository.save(device);
        } catch (Exception e) {
            throw new DeviceUpdateException("Error updating device: " + e.getMessage(), e);
        }
    }

    private Device buildDevice(String name, String brand, StateEnum state) {
        return Device.builder()
                .name(name)
                .brand(brand)
                .state(state)
                .build();
    }

}
