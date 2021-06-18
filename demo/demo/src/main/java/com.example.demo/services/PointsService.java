package com.example.demo.services;
import com.example.demo.domain.CustomerPoint;
import com.example.demo.domain.Transaction;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;

@Service
public class PointsService {

    public Long calculatePointsEarn(Double purchaseAmount) {

        long totalPointsEarned = 0;
        if (purchaseAmount >= 101) {
            int overOneHundredInDollars = (int) (purchaseAmount - 100 );
            long twoPointsPlusFifty = (overOneHundredInDollars * 2) + 50;
            totalPointsEarned += twoPointsPlusFifty;
        }

        if (purchaseAmount > 50 & purchaseAmount < 101) {
            int overFiftyInDollars = (int) (purchaseAmount - 50);
            long onePoint = overFiftyInDollars * 1;
            totalPointsEarned += onePoint;
        }
        return totalPointsEarned;
    }

    public List<CustomerPoint> getPoints(List<Transaction> transactions) {

        List<CustomerPoint> customerPoints = new ArrayList<>();
        Map<Long, Long> customerMapKey = new HashMap<>();
        Map<Long, Long> monthlyPointMap;
        Map<Long, Map<Long, Long>> customerMonthlyPointsMap = new HashMap<>();

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
            customerMonthlyPointsMap.putIfAbsent(customerYearMonth, monthlyPointMap);

            long pointsEarn = calculatePointsEarn(transaction.getTransAmount());
            if (pointsEarn != 0l) {
                monthlyPointMap = customerMonthlyPointsMap.get(customerYearMonth);
                long totalPointsEarned = pointsEarn + monthlyPointMap.get(yearMonth);
                monthlyPointMap.put(yearMonth, totalPointsEarned);
                customerMonthlyPointsMap.put(customerYearMonth, monthlyPointMap);
            }
        }

        customerMonthlyPointsMap.forEach((k,v) -> {
            long customerId = k;
            long customerIdNumber = customerMapKey.get(customerId);
            Map<Long, Long> monthlyPoints = customerMonthlyPointsMap.get(k);

            monthlyPoints.forEach((b,c) -> {
                if (c != 0) {

                    String yearMonthString = String.valueOf(b);
                    String yearString = yearMonthString.substring(0, 4);
                    String monthString = yearMonthString.substring(4, 6);
                    String yearMonth = (yearString + "-" + monthString);

                    CustomerPoint customerPoint =
                            new CustomerPoint(customerIdNumber, yearMonth, c);
                    customerPoints.add(customerPoint);
                }
            });
        });
        Collections.sort(customerPoints, compareByCustomerIdYearMonth);
        return customerPoints;
    }

}

