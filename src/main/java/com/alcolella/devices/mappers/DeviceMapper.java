package com.alcolella.devices.mappers;

import com.alcolella.devices.adapter.input.dto.DeviceResponseDTO;
import com.alcolella.devices.domain.dto.DeviceDTO;
import com.alcolella.devices.domain.entities.Device;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    DeviceResponseDTO toDeviceResponseDTO(Device device);
    DeviceDTO toDeviceDTO(Device device);

}
