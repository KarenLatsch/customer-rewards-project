package com.example.demo.services;
import com.example.demo.domain.CustomerPoint;
import com.example.demo.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PointsService {

//    public Long getRewards(Double purchaseAmount) {
//
//        Integer totalPoints =0;
//
//      if (purchaseAmount > 100) {
//          Integer overOneHunred = purchaseAmount - 100;
//          Integer twoPoint = overOneHunred * 2;
//          totalPoints += twoPoint;
//        }
//
//      if (purchaseAmount > 50){
//     //   ? Integer overFifty = purchaseAmount - 50;
//         Integer onePoint = 50 * 1;
//         totalPoints += onePoint;
//      }
//        return totalPoints;
//    }


    public List<CustomerPoint> getPoints(List<Transaction> transactions) {

        List<CustomerPoint> customerPoints = new ArrayList<>();

        for (Transaction transaction :
                transactions) {
//            customerPoints.add(customerId, )
        }



        return customerPoints;
    }
}
