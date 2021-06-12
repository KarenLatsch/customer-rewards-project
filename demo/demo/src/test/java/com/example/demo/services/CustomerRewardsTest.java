package com.example.demo.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRewardsTest {

    CustomerRewards customerRewards;
    @BeforeAll
    void setUp() {
        customerRewards = new CustomerRewards();
    }

    @Test
    Integer getRewards() {


        Integer calculated = 110;

      assertEquals(calculated, customerRewards.getRewards(120));


        return getRewards();
    }

}


