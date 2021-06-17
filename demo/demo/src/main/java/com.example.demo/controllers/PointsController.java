package com.example.demo.controllers;

import com.example.demo.domain.CustomerPoint;
import com.example.demo.domain.Transaction;
import com.example.demo.services.PointsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PointsController {

    private PointsService pointsService;

    public PointsController(PointsService pointsService) {
        this.pointsService = pointsService;
    }

    @PostMapping("/points")
    List<CustomerPoint> receiveTransactions(@RequestBody List<Transaction> transactions){

       List<CustomerPoint> customersPoints = pointsService.getPoints(transactions);

       return customersPoints;
    }

}
