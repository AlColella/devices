package com.alcolella.devices.exceptions;

public class DeviceInUseException  extends RuntimeException {
    public DeviceInUseException(String message) {
        super(message);
    }
}
