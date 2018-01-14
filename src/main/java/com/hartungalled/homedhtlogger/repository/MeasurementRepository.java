package com.hartungalled.homedhtlogger.repository;

import com.hartungalled.homedhtlogger.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
