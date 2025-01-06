package com.railway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.railway.dto.PassengerDTO;

@Controller
@RequestMapping("/passenger")
public class PassengerController {

    private final TrainController.PassengerService passengerService;

    public <PassengerService> PassengerController(PassengerService passengerService) {
        this.passengerService = (TrainController.PassengerService) passengerService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "passenger/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerPassenger(@RequestBody PassengerDTO passengerDTO) {
        passengerService.registerPassenger(passengerDTO);
        return "Passenger registered successfully";
    }
}
