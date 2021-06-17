package com.example.demo.services;

import com.example.demo.domain.CustomerPoint;
import com.example.demo.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PointsServiceTest {

    PointsService pointsService;

    @BeforeEach
    void setUp() {
        pointsService = new PointsService();
    }

    @Test
    void earnedOnePointsPlusTwoPoints() {

        // execute test
        long totalPointsEarned = pointsService.calculatePointsEarn(120.99);
        assertThat(totalPointsEarned).isEqualTo(90);

        // execute test
        totalPointsEarned = pointsService.calculatePointsEarn(202.99);
        assertThat(totalPointsEarned).isEqualTo(254);
    }

    @Test
    void earnedOnePointsOnly() {

        // execute test
        long totalPointsEarned = pointsService.calculatePointsEarn(100.99);
        assertThat(totalPointsEarned).isEqualTo(50);

        // execute test
        totalPointsEarned = pointsService.calculatePointsEarn(59.99);
        assertThat(totalPointsEarned).isEqualTo(9);

        // execute test
        totalPointsEarned = pointsService.calculatePointsEarn(100.00);
        assertThat(totalPointsEarned).isEqualTo(50);
    }

    @Test
    void earnedZeroPoints() {

        // execute test
        long totalPointsEarned = pointsService.calculatePointsEarn(0.99);
        assertThat(totalPointsEarned).isEqualTo(0);

        // execute test
         totalPointsEarned = pointsService.calculatePointsEarn(15.99);
        assertThat(totalPointsEarned).isEqualTo(0);

        // execute test
        totalPointsEarned = pointsService.calculatePointsEarn(50.99);
        assertThat(totalPointsEarned).isEqualTo(0);

        // execute test
        totalPointsEarned = pointsService.calculatePointsEarn(50.00);
        assertThat(totalPointsEarned).isEqualTo(0);
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
        secondTransaction.setTransAmount(51.00);

        Transaction thirdTransaction = new Transaction();
        thirdTransaction.setCustomerId(1l);
        thirdTransaction.setTransDate(LocalDate.of(2020, 1, 30));
        thirdTransaction.setTransAmount(120.99);

        transactions.add(firstTransaction);
        transactions.add(secondTransaction);
        transactions.add(thirdTransaction);

        // execute test
       List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

       assertThat(customerPoints).contains(
               new CustomerPoint(1l,202001l, 91l));
    }

//    @Test
//    void calculatesPointsForMultipleCustomerInSingleMonthWithMultipleTransactions() {
//        // set up test data
//        List<Transaction> transactions = new ArrayList<>();
//
//        Transaction firstTransaction = new Transaction();
//        firstTransaction.setCustomerId(1l);
//        firstTransaction.setTransDate(LocalDate.of(2020, 2, 11));
//        firstTransaction.setTransAmount(49.00);
//
//        Transaction secondTransaction = new Transaction();
//        secondTransaction.setCustomerId(1l);
//        secondTransaction.setTransDate(LocalDate.of(2020, 2, 25));
//        secondTransaction.setTransAmount(101.00);
//
//        Transaction thirdTransaction = new Transaction();
//        thirdTransaction.setCustomerId(2l);
//        thirdTransaction.setTransDate(LocalDate.of(2020, 2, 8));
//        thirdTransaction.setTransAmount(16.00);
//
//        Transaction fourthTransaction = new Transaction();
//        fourthTransaction.setCustomerId(2l);
//        fourthTransaction.setTransDate(LocalDate.of(2020, 2, 6));
//        fourthTransaction.setTransAmount(11.00);
//
//        Transaction fifthTransaction = new Transaction();
//        fifthTransaction.setCustomerId(2l);
//        fifthTransaction.setTransDate(LocalDate.of(2020, 2, 1));
//        fifthTransaction.setTransAmount(55.00);
//
//        transactions.add(firstTransaction);
//        transactions.add(secondTransaction);
//        transactions.add(thirdTransaction);
//        transactions.add(fourthTransaction);
//        transactions.add(fifthTransaction);
//
//        // execute test
//       List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);
//
//       // check results
//       assertThat(customerPoints)
//               .contains(
//                      new CustomerPoint(1l,202002l, 52l),
//                      new CustomerPoint(2l,202002l,  5l)
//                );
//     }

    @Test
    void calculatesPointsForSingleCustomerInMultipleMonthWithMultipleTransactions() {
        // set up test data
        List<Transaction> transactions = new ArrayList<>();

        Transaction firstTransaction = new Transaction();
        firstTransaction.setCustomerId(1l);
        firstTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        firstTransaction.setTransAmount(100.00);

        Transaction secondTransaction = new Transaction();
        secondTransaction.setCustomerId(1l);
        secondTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        secondTransaction.setTransAmount(120.00);

        Transaction thirdTransaction = new Transaction();
        thirdTransaction.setCustomerId(1l);
        thirdTransaction.setTransDate(LocalDate.of(2020, 3, 1));
        thirdTransaction.setTransAmount(51.00);

        Transaction fourthTransaction = new Transaction();
        fourthTransaction.setCustomerId(1l);
        fourthTransaction.setTransDate(LocalDate.of(2020, 3, 1));
        fourthTransaction.setTransAmount(16.00);

        Transaction fifthTransaction = new Transaction();
        fifthTransaction.setCustomerId(1l);
        fifthTransaction.setTransDate(LocalDate.of(2020, 3, 1));
        fifthTransaction.setTransAmount(12.00);

        transactions.add(firstTransaction);
        transactions.add(secondTransaction);
        transactions.add(thirdTransaction);
        transactions.add(fourthTransaction);
        transactions.add(fifthTransaction);

        // execute test
       List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

       // check results
        assertThat(customerPoints)
                .contains(
                       new CustomerPoint(1l, 202002l,  140l),
                       new CustomerPoint(1l,202003l,  1l)
               );
    }

    @Test
    void calculatesPointsForMultipleCustomerInMultipleMonthWithMultipleTransactions() {
        // set up test data
        List<Transaction> transactions = new ArrayList<>();

        Transaction firstTransaction = new Transaction();
        firstTransaction.setCustomerId(1l);
        firstTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        firstTransaction.setTransAmount(51.00);

        Transaction secondTransaction = new Transaction();
        secondTransaction.setCustomerId(1l);
        secondTransaction.setTransDate(LocalDate.of(2020, 3, 1));
        secondTransaction.setTransAmount(120.00);

        Transaction thirdTransaction = new Transaction();
        thirdTransaction.setCustomerId(1l);
        thirdTransaction.setTransDate(LocalDate.of(2020, 3, 1));
        thirdTransaction.setTransAmount(1.00);

        Transaction fourthTransaction = new Transaction();
        fourthTransaction.setCustomerId(1l);
        fourthTransaction.setTransDate(LocalDate.of(2020, 4, 1));
        fourthTransaction.setTransAmount(100.00);

        Transaction fifthTransaction = new Transaction();
        fifthTransaction.setCustomerId(1l);
        fifthTransaction.setTransDate(LocalDate.of(2020, 3, 1));
        fifthTransaction.setTransAmount(1.00);

        Transaction sixTransaction = new Transaction();
        sixTransaction.setCustomerId(2l);
        sixTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        sixTransaction.setTransAmount(55.00);

        Transaction sevenTransaction = new Transaction();
        sevenTransaction.setCustomerId(2l);
        sevenTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        sevenTransaction.setTransAmount(1.00);

        Transaction eightTransaction = new Transaction();
        eightTransaction.setCustomerId(2l);
        eightTransaction.setTransDate(LocalDate.of(2020, 3, 1));
        eightTransaction.setTransAmount(120.00);

        Transaction nineTransaction = new Transaction();
        nineTransaction.setCustomerId(2l);
        nineTransaction.setTransDate(LocalDate.of(2020, 3, 1));
        nineTransaction.setTransAmount(1.00);

        Transaction tenTransaction = new Transaction();
        tenTransaction.setCustomerId(2l);
        tenTransaction.setTransDate(LocalDate.of(2020, 3, 1));
        tenTransaction.setTransAmount(1.00);

        transactions.add(firstTransaction);
        transactions.add(secondTransaction);
        transactions.add(thirdTransaction);
        transactions.add(fourthTransaction);
        transactions.add(fifthTransaction);
        transactions.add(sixTransaction);
        transactions.add(sevenTransaction);
        transactions.add(eightTransaction);
        transactions.add(nineTransaction);
        transactions.add(tenTransaction);

        // execute test
        List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

        // check results
         assertThat(customerPoints)
                 .contains(
                         new CustomerPoint( 1l, 202002l, 1l),
                         new CustomerPoint( 1l, 202003l, 90l),
                         new CustomerPoint( 1l, 202004l, 50l),
                         new CustomerPoint( 2l, 202002l, 5l),
                         new CustomerPoint( 2l, 202003l, 120l)
                 );
  }

    @Test
    void calculatesPointsForSingleCustomerInSingleMonthWithSingleTransactions() {
        // set up test data
        List<Transaction> transactions = new ArrayList<>();

        Transaction firstTransaction = new Transaction();
        firstTransaction.setCustomerId(1l);
        firstTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        firstTransaction.setTransAmount(145.00);

        transactions.add(firstTransaction);

        // execute test
         List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

         assertThat(customerPoints).contains(
                new CustomerPoint(1l,202002l, 140l));
    }


    @Test
    void calculatesPointsForMultipleCustomerInMultipleMonthWithSingleTransactions() {
        // set up test data
        List<Transaction> transactions = new ArrayList<>();

        Transaction firstTransaction = new Transaction();
        firstTransaction.setCustomerId(1l);
        firstTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        firstTransaction.setTransAmount(120.00);

        Transaction secondTransaction = new Transaction();
        secondTransaction.setCustomerId(2l);
        secondTransaction.setTransDate(LocalDate.of(2020, 3, 1));
        secondTransaction.setTransAmount(51.00);

        transactions.add(firstTransaction);
        transactions.add(secondTransaction);

        // execute test
       List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

//        // check results
       assertThat(customerPoints)
                .contains(
                       new CustomerPoint(1l, 202002l, 90l),
                       new CustomerPoint(2l, 202003l, 1l)
                );

    }
}


