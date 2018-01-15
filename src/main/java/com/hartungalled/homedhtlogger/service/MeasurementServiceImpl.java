package com.hartungalled.homedhtlogger.service;

import com.hartungalled.homedhtlogger.model.Measurement;
import com.hartungalled.homedhtlogger.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementServiceImpl {

    @Autowired
    MeasurementRepository measurementRepository;

    public void save(Measurement measurement) {
        measurementRepository.save(measurement);
    }

}