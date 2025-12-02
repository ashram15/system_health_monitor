package com.first.system_mointor_sensor;

import jakarta.persistence.*;
import java.time.Instant;

@Entity // Tells Spring this is a database table
public class SystemMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hostname;
    private double cpuUsage;
    private double ramUsage;
    private double diskUsage;
    private Instant timestamp;

    // --- Getters and Setters ---
    // (Spring needs these to access the fields)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getRamUsage() {
        return ramUsage;
    }

    public void setRamUsage(double ramUsage) {
        this.ramUsage = ramUsage;
    }

    public double getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(double diskUsage) {
        this.diskUsage = diskUsage;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}