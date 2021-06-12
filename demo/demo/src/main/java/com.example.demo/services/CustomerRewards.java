package com.example.demo.services;
import org.springframework.stereotype.Service;


@Service
public class CustomerRewards {

    public Integer getRewards(Integer purchaseAmount) {

        Integer totalPoints =0;

      if (purchaseAmount > 100) {
          Integer overOneHunred = purchaseAmount - 100;
          Integer twoPoint = overOneHunred * 2;
          totalPoints += twoPoint;
        }

      if (purchaseAmount > 50){
         Integer overFifty = purchaseAmount - 50;
         Integer onePoint = overFifty * 1;
         totalPoints += onePoint;
      }
        return totalPoints;

    }
}
