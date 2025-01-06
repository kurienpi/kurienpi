package com.railway.controller;

import com.railway.dto.PassengerDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.railway.dto.TrainDTO;

@Controller
@RequestMapping("/train")
public class TrainController {

    private final com.railway.controller.TrainService trainService;

    public <TrainService> TrainController(TrainService trainService) {
        this.trainService = (com.railway.controller.TrainService) trainService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "train/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public String registerTrain(@RequestBody TrainDTO trainDTO) {
        trainService.registerTrain(trainDTO);
        return "Train registered successfully";
    }

    public static class PassengerService {
        public void registerPassenger(PassengerDTO passengerDTO) {
        }
    }
}