package com.example.demo.controllers;

import com.example.demo.domain.CustomerPoint;
import com.example.demo.domain.Transaction;
import com.example.demo.services.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PointsController {

    private PointsService pointsService;

    public PointsController(PointsService pointsService) {
        this.pointsService = pointsService;
    }

//
//    @GetMapping("/{purchaseAmount}")
//    Map<String, Integer> customerRewards(
//            @PathVariable Integer purchaseAmount
//     ){
//        Map<String, Integer> calulatedRewards = new HashMap<>();
//
//        Integer rewards = customerRewards.getRewards(purchaseAmount);
//        calulatedRewards.put("Total Reward Points", rewards);
//
//        return calulatedRewards;
//    }

    @PostMapping("/points")
    List<CustomerPoint> receiveTransactions(@RequestBody List<Transaction> transactions){

        List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

       return customerPoints;
    }
/*
 [
   {
     "customerId": 1,
     "transDate": "2020-01-30",
     "transAmount": 12.34
   },
   {
     "customerId": 2,
     "transDate": "2020-02-28",
     "transAmount": 100.50
   }
 ]
 */
}
