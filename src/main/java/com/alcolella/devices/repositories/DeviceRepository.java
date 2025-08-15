package com.alcolella.devices.repositories;

import com.alcolella.devices.domain.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
