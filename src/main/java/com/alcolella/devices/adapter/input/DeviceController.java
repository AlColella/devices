package com.alcolella.devices.adapter.input;

import com.alcolella.devices.adapter.input.dto.DeviceRequestDTO;
import com.alcolella.devices.domain.enums.StateEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Device Controller", description = "Controller for managing devices")
@RequestMapping("/devices")
public class DeviceController {

    @Operation(summary = "Get Device by ID", description = "Retrieve a device by its ID")
    @GetMapping("/{id}")
    public RequestEntity<Object> getDevice(
            @Parameter(description = "ID of the device to retrieve", required = true)
            @PathVariable Long id
    ) {
        return null;
    }

    @Operation(summary = "Get All Devices", description = "Retrieve all devices")
    @GetMapping("/getAllDevices")
    public RequestEntity<List<Object>> getAllDevices(
            @Parameter(description = "ID of the device to retrieve", required = true)
            @PathVariable Long id
    ) {
        return null;
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
    public ResponseEntity<Object> createDevice(
            @Parameter(description = "Device details to create", required = true)
            @RequestBody DeviceRequestDTO deviceRequest) {

        return null;
    }

}
