package com.alcolella.devices.domain.entities;

import com.alcolella.devices.domain.enums.StateEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_device")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StateEnum state;

    @Column(name = "creation_time", nullable = false, updatable = false)
    private Instant creationTime;

    @PrePersist
    public void prePersist() {
        this.creationTime =Instant.now();
    }
}
