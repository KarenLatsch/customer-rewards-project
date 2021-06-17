package com.example.demo.services;
import com.example.demo.domain.CustomerPoint;
import com.example.demo.domain.Transaction;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PointsService {

    public Long calculatePointsEarn(Double purchaseAmount) {

        long totalPointsEarned = 0;

        if (purchaseAmount > 101) {
            int overOneHundred = (int) (purchaseAmount - 100);
            long twoPoint = (overOneHundred * 2) + 50;
            totalPointsEarned += twoPoint;
        }

        if (purchaseAmount > 50 & purchaseAmount < 101) {
            int overFifty = (int) (purchaseAmount - 50);
            long onePoint = overFifty * 1;
            totalPointsEarned += onePoint;
        }
        return totalPointsEarned;
    }


    public List<CustomerPoint> getPoints(List<Transaction> transactions) {

        List<CustomerPoint> customerPoints = new ArrayList<>();
        Map<Long, Long> customerMapKey = new HashMap<>();
        Map<Long, Long> monthlyPointMap;
        Map<Long, Map<Long, Long>> customerMonthlyPoints = new HashMap<>();

        //Compare by first name and then last name
        Comparator<CustomerPoint> compareByCustomerIdYearMonth = Comparator
                .comparing(CustomerPoint::getCustomerId)
                .thenComparing(CustomerPoint::getYearMonth);

        for (Transaction transaction :
                transactions) {

            String transYear = String.valueOf(transaction.getTransDate().getYear());
            String transMonth = String.valueOf(transaction.getTransDate().getMonthValue());
            long yearMonth = Long.parseLong(transYear + transMonth);

            if (transaction.getTransDate().getMonthValue() < 10) {
                String yearMonthLeadingZero = String.valueOf("0" + transaction.getTransDate().getMonthValue());
                yearMonth = Long.parseLong((transYear + yearMonthLeadingZero));
            }

            long customerYearMonth = Long.parseLong(transaction.getCustomerId() + transYear + transMonth);

            monthlyPointMap = new HashMap<>();
            monthlyPointMap.put(yearMonth, 0l);

            customerMapKey.putIfAbsent(customerYearMonth, transaction.getCustomerId());
            customerMonthlyPoints.putIfAbsent(customerYearMonth, monthlyPointMap);

            long pointsEarn = calculatePointsEarn(transaction.getTransAmount());
            if (pointsEarn != 0l) {
                monthlyPointMap = customerMonthlyPoints.get(customerYearMonth);
                long totalPointsEarned = pointsEarn + monthlyPointMap.get(yearMonth);
                monthlyPointMap.put(yearMonth, totalPointsEarned);
                customerMonthlyPoints.put(customerYearMonth, monthlyPointMap);
            }
        }

        customerMonthlyPoints.forEach((k,v) -> {
            long customerId = k;
            long customerIdNumber = customerMapKey.get(customerId);
            Map<Long, Long> monthlyPoint = customerMonthlyPoints.get(k);

            monthlyPoint.forEach((b,c) -> {
                if (c != 0) {
                    CustomerPoint customerPoint =
                            new CustomerPoint(customerIdNumber, b, c);
                    customerPoints.add(customerPoint);
                }
            });

        });
        Collections.sort(customerPoints, compareByCustomerIdYearMonth);
        return customerPoints;
    }

}

