package com.first.system_mointor_sensor;

import org.springframework.data.jpa.repository.JpaRepository;

// We are telling Spring Data JPA to manage our SystemMetric object,
// and that its ID is of type Long.
public interface SystemMetricRepository extends JpaRepository<SystemMetric, Long> {
    // That's it! Spring Boot will automatically create all the code
    // for save(), findById(), findAll(), etc. We don't have to.
}