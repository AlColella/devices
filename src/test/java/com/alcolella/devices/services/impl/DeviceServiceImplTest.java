package com.alcolella.devices.services.impl;

import com.alcolella.devices.domain.entities.Device;
import com.alcolella.devices.domain.enums.StateEnum;
import com.alcolella.devices.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceServiceImplTest {

    private DeviceRepository deviceRepository;
    private DeviceServiceImpl deviceService;

    @BeforeEach
    void setUp() {
        deviceRepository = mock(DeviceRepository.class);
        deviceService = new DeviceServiceImpl(deviceRepository);
    }

    @Test
    void findAllByBrandTest() {
        String brand = "BrandA";
        Device device = new Device();
        when(deviceRepository.findAllByBrand(brand)).thenReturn(List.of(device));

        List<Device> result = deviceService.getDevicesByBrand(brand);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(deviceRepository).findAllByBrand(brand);
    }

    @Test
    void findAllByStateTest() {
        StateEnum state = StateEnum.AVAILABLE;
        Device device = new Device();
        when(deviceRepository.findAllByState(state)).thenReturn(List.of(device));

        List<Device> result = deviceService.getDevicesByState(state);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(deviceRepository).findAllByState(state);
    }


}