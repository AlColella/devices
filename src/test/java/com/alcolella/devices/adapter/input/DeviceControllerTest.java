package com.alcolella.devices.adapter.input;

import com.alcolella.devices.adapter.input.dto.DeviceResponseDTO;
import com.alcolella.devices.domain.dto.DeviceDTO;
import com.alcolella.devices.domain.entities.Device;
import com.alcolella.devices.domain.enums.StateEnum;
import com.alcolella.devices.mappers.DeviceMapper;
import com.alcolella.devices.services.DeviceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DeviceService deviceService;

    @MockitoBean
    private DeviceMapper deviceMapper;


    @Test
    void getDeviceByIdTest() throws Exception {
        var responseDTO = new DeviceResponseDTO();
        Mockito.when(deviceService.getDeviceById(1L)).thenReturn( Optional.of(new Device()));
        Mockito.when(deviceMapper.toDeviceResponseDTO(any())).thenReturn(responseDTO);

        mockMvc.perform(get("/devices/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getDeviceByIdNotFountTest() throws Exception {
        Mockito.when(deviceService.getDeviceById(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/devices/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllDevicesTest() throws Exception {
        Mockito.when(deviceService.getAllDevices()).thenReturn(List.of(new Device()));
        Mockito.when(deviceMapper.toDeviceResponseDTO(any())).thenReturn(new DeviceResponseDTO());

        mockMvc.perform(get("/devices/getAllDevices"))
                .andExpect(status().isOk());
    }

    @Test
    void createDeviceTest() throws Exception {
        var requestJson = """
                {
                  "name": "Device1",
                  "brand": "BrandA",
                  "state": "IN_USE"
                }
                """;
        Mockito.when(deviceService.createDevice(any(), any(), any())).thenReturn(new Device());
        Mockito.when(deviceMapper.toDeviceDTO(any())).thenReturn(new DeviceDTO());

        mockMvc.perform(post("/devices/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateDeviceTest() throws Exception {
        var requestJson = """
                {
                  "name": "Device1",
                  "brand": "BrandA",
                  "state": "IN_USE"
                }
                """;
        Mockito.when(deviceService.updateDevice(eq(1L), any(), any(), any())).thenReturn(new Device());
        Mockito.when(deviceMapper.toDeviceResponseDTO(any())).thenReturn(new DeviceResponseDTO());

        mockMvc.perform(put("/devices/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDeviceTest() throws Exception {
        mockMvc.perform(delete("/devices/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getDevicesByBrandTest() throws Exception {
        String brand = "BrandA";
        var device = new Device();
        var responseDTO = new DeviceResponseDTO();

        Mockito.when(deviceService.getDevicesByBrand(brand)).thenReturn(List.of(device));
        Mockito.when(deviceMapper.toDeviceResponseDTO(device)).thenReturn(responseDTO);

        mockMvc.perform(get("/devices/getDevicesByBrand/{brand}", brand))
                .andExpect(status().isOk());
    }

    @Test
    void getDevicesByStateTest() throws Exception {
        StateEnum state = StateEnum.AVAILABLE;
        var device = new Device();
        var responseDTO = new DeviceResponseDTO();

        Mockito.when(deviceService.getDevicesByState(state)).thenReturn(List.of(device));
        Mockito.when(deviceMapper.toDeviceResponseDTO(device)).thenReturn(responseDTO);

        mockMvc.perform(get("/devices/getDevicesByState/{state}", state))
                .andExpect(status().isOk());
    }
}