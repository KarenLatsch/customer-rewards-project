package com.example.demo.services;

import com.example.demo.domain.CustomerPoint;
import com.example.demo.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PointsServiceTest {

    PointsService pointsService;

    @BeforeEach
    void setUp() {
        pointsService = new PointsService();
    }

    @Test
    void calculatesPointsForSingleCustomerInSingleMonthWithMultipleTransactions() {
        // set up test data
        List<Transaction> transactions = new ArrayList<>();

        Transaction firstTransaction = new Transaction();
        firstTransaction.setCustomerId(1l);
        firstTransaction.setTransDate(LocalDate.of(2020, 1, 1));
        firstTransaction.setTransAmount(1.00);

        Transaction secondTransaction = new Transaction();
        secondTransaction.setCustomerId(1l);
        secondTransaction.setTransDate(LocalDate.of(2020, 1, 30));
        secondTransaction.setTransAmount(1.00);

        transactions.add(firstTransaction);
        transactions.add(secondTransaction);

        // execute test
        List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

        // check results
        CustomerPoint expectedToExist = new CustomerPoint();
        expectedToExist.setPoints(0l);
        expectedToExist.setCustomerId(1l);
        expectedToExist.setPointPeriod(LocalDate.of(2020, 1, 1));

        assertThat(customerPoints)
                .hasSize(1)
                .containsOnly(expectedToExist);
    }

    @Test
    void calculatesPointsForMultipleCustomerInSingleMonthWithMultipleTransactions() {
        // set up test data
        List<Transaction> transactions = new ArrayList<>();

        Transaction firstTransaction = new Transaction();
        firstTransaction.setCustomerId(1l);
        firstTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        firstTransaction.setTransAmount(100.00);

        Transaction secondTransaction = new Transaction();
        secondTransaction.setCustomerId(1l);
        secondTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        secondTransaction.setTransAmount(100.00);

        // execute test
        List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

        List<CustomerPoint> expectedCustomerPoints = new ArrayList<>();
        // customer with id 1 date of 2021-01-01 and points of 0
        // customer ... blah blah

        // check results
        assertThat(customerPoints)
                .containsExactlyInAnyOrder(
                        new CustomerPoint(),
                        new CustomerPoint(),
                        new CustomerPoint()
                );
    }

    @Test
    void calculatesPointsForSingleCustomerInMultipleMonthWithMultipleTransactions() {

    }

    @Test
    void calculatesPointsForMultipleCustomerInMultipleMonthWithMultipleTransactions() {

    }

    @Test
    void calculatesPointsForSingleCustomerInSingleMonthWithSingleTransactions() {

    }

    @Test
    void calculatesPointsForMultipleCustomerInMultipleMonthWithSingleTransactions() {

    }
}


