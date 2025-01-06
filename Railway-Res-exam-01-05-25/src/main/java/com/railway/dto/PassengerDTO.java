package com.railway.dto;

import lombok.Data;
import com.railway.enums.PassengerType;

@Data
public class PassengerDTO {
    private String name;
    private String email;
    private String mobile;
    private PassengerType passengerType;
    private String seniorCitizenId;
    private String physicalHandicappedProof;
}
