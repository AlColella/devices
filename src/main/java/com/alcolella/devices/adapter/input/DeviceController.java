package com.alcolella.devices.adapter.input;

import com.alcolella.devices.adapter.input.dto.DeviceRequestDTO;
import com.alcolella.devices.adapter.input.dto.DeviceResponseDTO;
import com.alcolella.devices.domain.dto.DeviceDTO;
import com.alcolella.devices.domain.enums.StateEnum;
import com.alcolella.devices.mappers.DeviceMapper;
import com.alcolella.devices.services.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "Device Controller", description = "Controller for managing devices")
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;
    private final DeviceMapper deviceMapper;

    public DeviceController(DeviceService deviceService, DeviceMapper deviceMapper) {
        this.deviceService = deviceService;
        this.deviceMapper = deviceMapper;
    }

    @Operation(summary = "Get Device by ID", description = "Retrieve a device by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> getDevice(
            @Parameter(description = "ID of the device to retrieve", required = true)
            @PathVariable Long id
    ) {
        return deviceService.getDeviceById(id)
                .map(deviceMapper::toDeviceResponseDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get All Devices", description = "Retrieve all devices")
    @GetMapping("/getAllDevices")
    public ResponseEntity<List<DeviceResponseDTO>> getAllDevices()
    {
        var deviceList = deviceService.getAllDevices()
                .stream()
                .map(deviceMapper::toDeviceResponseDTO)
                .toList();
        return ResponseEntity.ok(deviceList);
    }

    @Operation(summary = "Get Devices by Brand", description = "Retrieve devices by their brand")
    @GetMapping("/getDevicesByBrand/{brand}")
    public RequestEntity<List<Object>> getDevicesByBrand(
            @Parameter(description = "Brand of the devices to retrieve", required = true)
            @PathVariable String brand
    ) {
        return null;
    }

    @Operation(summary = "Get Devices by State", description = "Retrieve devices by their state")
    @GetMapping("/getDevicesByState/{state}")
    public RequestEntity<List<Object>> getDevicesByState(
            @Parameter(description = "Brand of the devices to retrieve", required = true)
            @PathVariable StateEnum state
    ) {
        return null;
    }


    @Operation(summary = "Create Device", description = "Create a new device")
    @PostMapping("/create")
    public ResponseEntity<DeviceDTO> createDevice(@Valid
            @Parameter(description = "Device details to create", required = true)
            @RequestBody DeviceRequestDTO deviceRequestDTO) {


        var createdDevice = deviceService.createDevice(
                deviceRequestDTO.getName(),
                deviceRequestDTO.getBrand(),
                deviceRequestDTO.getState()
        ) ;
        var created = deviceMapper.toDeviceDTO(createdDevice);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Device", description = "Update an existing device by ID")
    public ResponseEntity<Object> updateDevice(
            @Parameter(description = "ID of the device to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated device details", required = true)
            @RequestBody DeviceRequestDTO deviceRequest) {
        return null;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Device", description = "Delete a device by ID")
    public ResponseEntity<Object> deleteDevice(
            @Parameter(description = "ID of the device to delete", required = true)
            @PathVariable Long id) {
        return null;
    }

}
