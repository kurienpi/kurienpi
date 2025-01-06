package com.railway.model;

import com.railway.enums.TrainType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "trains")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainName;
    private Integer noOfCoaches;
    private String startStation;
    private String endStation;

    @Enumerated(EnumType.STRING)
    private TrainType trainType;

    private Double avgSpeed;
}
