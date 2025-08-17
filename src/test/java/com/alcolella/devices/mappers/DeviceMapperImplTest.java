package com.alcolella.devices.mappers;

import com.alcolella.devices.adapter.input.dto.DeviceResponseDTO;
import com.alcolella.devices.domain.entities.Device;
import com.alcolella.devices.domain.dto.DeviceDTO;
import com.alcolella.devices.domain.enums.StateEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceMapperImplTest {

    private final DeviceMapperImpl mapper = new DeviceMapperImpl();

    @Test
    void toDeviceDtoTest() {
        Device device = Device.builder()
                .id(1L)
                .name("Device1")
                .brand("BrandA")
                .state(StateEnum.AVAILABLE)
                .build();

        DeviceDTO dto = mapper.toDeviceDTO(device);

        assertNotNull(dto);
        assertEquals(device.getId(), dto.getId());
        assertEquals(device.getName(), dto.getName());
        assertEquals(device.getBrand(), dto.getBrand());
    }

    @Test
    void toDeviceResponseDTOTest() {
        Device device = Device.builder()
                .id(1L)
                .name("Device1")
                .brand("BrandA")
                .state(StateEnum.AVAILABLE)
                .build();

        DeviceResponseDTO dto = mapper.toDeviceResponseDTO(device);

        assertNotNull(dto);
        assertEquals(device.getId(), dto.getId());
        assertEquals(device.getName(), dto.getName());
        assertEquals(device.getBrand(), dto.getBrand());
    }

}