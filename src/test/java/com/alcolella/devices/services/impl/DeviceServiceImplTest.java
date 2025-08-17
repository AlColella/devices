package com.alcolella.devices.services.impl;

import com.alcolella.devices.domain.entities.Device;
import com.alcolella.devices.domain.enums.StateEnum;
import com.alcolella.devices.exceptions.DeviceInUseException;
import com.alcolella.devices.exceptions.DeviceNotFoundException;
import com.alcolella.devices.exceptions.DeviceUpdateException;
import com.alcolella.devices.repositories.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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

    @Test
    void createDeviceTest() {
        String name = "Device1";
        String brand = "BrandA";
        StateEnum state = StateEnum.AVAILABLE;
        Device deviceToSave = Device.builder().name(name).brand(brand).state(state).build();
        Device savedDevice = Device.builder().name(name).brand(brand).state(state).build();

        when(deviceRepository.save(any(Device.class))).thenReturn(savedDevice);

        Device result = deviceService.createDevice(name, brand, state);

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(brand, result.getBrand());
        assertEquals(state, result.getState());
        verify(deviceRepository).save(any(Device.class));
    }

    @Test
    void getDeviceByIdExistsTest() {
        Long id = 1L;
        Device device = new Device();
        when(deviceRepository.findById(id)).thenReturn(Optional.of(device));

        Optional<Device> result = deviceService.getDeviceById(id);

        assertTrue(result.isPresent());
        assertEquals(device, result.get());
        verify(deviceRepository).findById(id);
    }

    @Test
    void getDeviceByIdNotFoundTest() {
        Long id = 2L;
        when(deviceRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Device> result = deviceService.getDeviceById(id);

        assertFalse(result.isPresent());
        verify(deviceRepository).findById(id);
    }

    @Test
    void getAllDevicesTest() {
        Device device1 = new Device();
        Device device2 = new Device();
        when(deviceRepository.findAll()).thenReturn(List.of(device1, device2));

        List<Device> result = deviceService.getAllDevices();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(deviceRepository).findAll();
    }

    @Test
    void deleteDeviceInUseTest() {
        Long id = 3L;
        Device device = Device.builder().id(id).state(StateEnum.IN_USE).build();
        when(deviceRepository.findById(id)).thenReturn(Optional.of(device));

        assertThrows(DeviceInUseException.class, () -> deviceService.deleteDevice(id));
        verify(deviceRepository, never()).deleteById(anyLong());
    }

    @Test
    void updateDeviceSuccessTest() {
        Long id = 1L;
        Device device = Device.builder().id(id).name("OldName").brand("OldBrand").state(StateEnum.AVAILABLE).build();
        when(deviceRepository.findById(id)).thenReturn(Optional.of(device));
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        Device result = deviceService.updateDevice(id, "NewName", "NewBrand", StateEnum.IN_USE);

        assertEquals("NewName", result.getName());
        assertEquals("NewBrand", result.getBrand());
        assertEquals(StateEnum.IN_USE, result.getState());
        verify(deviceRepository).save(device);
    }

    @Test
    void updateDeviceNotFoundTest() {
        Long id = 2L;
        when(deviceRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DeviceNotFoundException.class, () -> deviceService.updateDevice(id, "Name", "Brand", StateEnum.AVAILABLE));
        verify(deviceRepository, never()).save(any(Device.class));
    }

    @Test
    void updateDeviceInUseNameOrBrandTest() {
        Long id = 3L;
        Device device = Device.builder().id(id).name("Name").brand("Brand").state(StateEnum.IN_USE).build();
        when(deviceRepository.findById(id)).thenReturn(Optional.of(device));

        assertThrows(DeviceInUseException.class, () -> deviceService.updateDevice(id, "OtherName", "Brand", StateEnum.IN_USE));
        assertThrows(DeviceInUseException.class, () -> deviceService.updateDevice(id, "Name", "OtherBrand", StateEnum.IN_USE));
        verify(deviceRepository, never()).save(any(Device.class));
    }

    @Test
    void updateDeviceSaveErrorTest() {
        Long id = 4L;
        Device device = Device.builder().id(id).name("Name").brand("Brand").state(StateEnum.AVAILABLE).build();
        when(deviceRepository.findById(id)).thenReturn(Optional.of(device));
        when(deviceRepository.save(any(Device.class))).thenThrow(new RuntimeException("DB error"));

        assertThrows(DeviceUpdateException.class, () -> deviceService.updateDevice(id, "Name", "Brand", StateEnum.IN_USE));
    }

}