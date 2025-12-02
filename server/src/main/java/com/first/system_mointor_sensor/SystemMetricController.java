package com.first.system_mointor_sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.List; // <--- Added this import!

@RestController
@RequestMapping("/api/v1/metrics") // This is your Base URL
public class SystemMetricController {

    @Autowired
    private SystemMetricRepository metricRepository;

    // 1. SAVE DATA (The one you already had)
    @PostMapping
    public ResponseEntity<SystemMetric> receiveMetric(@RequestBody SystemMetric metric) {
        metric.setTimestamp(Instant.now());
        SystemMetric savedMetric = metricRepository.save(metric);
        return ResponseEntity.ok(savedMetric);
    }

    // 2. GET DATA (The new part for the video!)
    // Since we didn't put a path inside ("..."), it uses the base URL.
    @GetMapping
    public List<SystemMetric> getAllMetrics() {
        return metricRepository.findAll();
    }
}