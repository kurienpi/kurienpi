package com.railway.model;

import com.railway.enums.PassengerType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String mobile;

    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    private String seniorCitizenId;
    private String physicalHandicappedProof;
}
