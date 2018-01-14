package com.hartungalled.homedhtlogger.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity @Table(name="measurements")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class Measurement {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull private Integer temperature;
    @NonNull private Integer humidity;

    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date timestamp;

}
