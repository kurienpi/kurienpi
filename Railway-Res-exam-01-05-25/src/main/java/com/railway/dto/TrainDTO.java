package com.railway.dto;

import lombok.Data;
import com.railway.enums.TrainType;

@Data
public class TrainDTO {
    private String trainName;
    private Integer noOfCoaches;
    private String startStation;
    private String endStation;
    private TrainType trainType;
    private Double avgSpeed;
}