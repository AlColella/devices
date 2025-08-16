package com.alcolella.devices.exceptions;

public class DeviceUpdateException  extends RuntimeException {

    public DeviceUpdateException(String message) {
        super(message);
    }
    public DeviceUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
