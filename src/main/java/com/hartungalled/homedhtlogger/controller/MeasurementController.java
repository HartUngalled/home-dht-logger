package com.hartungalled.homedhtlogger.controller;

import com.hartungalled.homedhtlogger.model.Measurement;
import com.hartungalled.homedhtlogger.service.MeasurementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dht")
public class MeasurementController {

    @Autowired
    private MeasurementServiceImpl measurementService;

    @GetMapping
    public List<Measurement> getAll() {
        return measurementService.getAll();
    }

    @PostMapping("/savemeasurement")
    public void save(@RequestBody Measurement measurement){
        measurementService.save(measurement);
    }

}