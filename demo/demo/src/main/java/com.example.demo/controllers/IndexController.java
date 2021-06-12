package com.example.demo.controllers;

import com.example.demo.services.CustomerRewards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

    private CustomerRewards customerRewards;

    @Autowired
    public void setCustomerReward(CustomerRewards customerRewards) {
        this.customerRewards = customerRewards;
    }
    Map<String, Integer> calulatedRewards = new HashMap<>();

    @GetMapping("/{customer}/{purchaseAmount}")

    public Map customerRewards(
            @PathVariable Integer purchaseAmount,
            @PathVariable String customer
    ){

//        Map<String, Integer> calulatedRewards = new HashMap<>();

        Integer rewards = customerRewards.getRewards(purchaseAmount);
        calulatedRewards.put(customer, rewards);
          return calulatedRewards;
    }

}
