// Make sure this package name matches the one in your Main.java file
package org.example;


public class SystemMetric {

    private String hostname;
    private double cpuUsage;
    private double ramUsage;
    private double diskUsage;

    // --- Getters and Setters ---
    // Jackson (the JSON library) needs these to build the JSON string

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
}