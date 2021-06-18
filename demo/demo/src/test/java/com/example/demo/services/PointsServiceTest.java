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
    void purchaseAmountIsUnderFifty() {
        long totalPointsEarned = pointsService.calculatePointsEarn(23.99);
        assertThat(totalPointsEarned).isEqualTo(0);
    }

    @Test
    void purchaseAmountIsFiftyOrGreaterAndLessThenFiftyOne() {
        long totalPointsEarned = pointsService.calculatePointsEarn(50.00);
        assertThat(totalPointsEarned).isEqualTo(0);

        totalPointsEarned = pointsService.calculatePointsEarn(50.99);
        assertThat(totalPointsEarned).isEqualTo(0);
    }

    @Test
    void purchaseAmountIsFiftyOneOrGreaterAndLessThanOneHundredAndOne() {
        long totalPointsEarned = pointsService.calculatePointsEarn(51.80);
        assertThat(totalPointsEarned).isEqualTo(1);

        totalPointsEarned = pointsService.calculatePointsEarn(55.99);
        assertThat(totalPointsEarned).isEqualTo(5);

        totalPointsEarned = pointsService.calculatePointsEarn(100.99);
        assertThat(totalPointsEarned).isEqualTo(50);
    }

    @Test
    void purchaseAmountIsOneHundredAndOneOrGreater() {
        long totalPointsEarned = pointsService.calculatePointsEarn(101.89);
        assertThat(totalPointsEarned).isEqualTo(52);

        totalPointsEarned = pointsService.calculatePointsEarn(120.99);
        assertThat(totalPointsEarned).isEqualTo(90);

        totalPointsEarned = pointsService.calculatePointsEarn(250.99);
        assertThat(totalPointsEarned).isEqualTo(350);
    }

    @Test
    void calculatesPointsForSingleCustomerInSingleMonthWithMultipleTransactions() {
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

       List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

       assertThat(customerPoints).contains(
               new CustomerPoint(1l,"2020-01", 91l));
    }

    @Test
    void calculatesPointsForMultipleCustomerInSingleMonthWithMultipleTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction firstTransaction = new Transaction();
        firstTransaction.setCustomerId(1l);
        firstTransaction.setTransDate(LocalDate.of(2020, 2, 11));
        firstTransaction.setTransAmount(49.00);

        Transaction secondTransaction = new Transaction();
        secondTransaction.setCustomerId(1l);
        secondTransaction.setTransDate(LocalDate.of(2020, 2, 25));
        secondTransaction.setTransAmount(101.00);

        Transaction thirdTransaction = new Transaction();
        thirdTransaction.setCustomerId(2l);
        thirdTransaction.setTransDate(LocalDate.of(2020, 2, 8));
        thirdTransaction.setTransAmount(16.00);

        Transaction fourthTransaction = new Transaction();
        fourthTransaction.setCustomerId(2l);
        fourthTransaction.setTransDate(LocalDate.of(2020, 2, 6));
        fourthTransaction.setTransAmount(11.00);

        Transaction fifthTransaction = new Transaction();
        fifthTransaction.setCustomerId(2l);
        fifthTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        fifthTransaction.setTransAmount(55.00);

        transactions.add(firstTransaction);
        transactions.add(secondTransaction);
        transactions.add(thirdTransaction);
        transactions.add(fourthTransaction);
        transactions.add(fifthTransaction);

        List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

        assertThat(customerPoints)
               .contains(
                      new CustomerPoint(1l,"2020-02", 52l),
                      new CustomerPoint(2l,"2020-02",  5l)
                );
     }

    @Test
    void calculatesPointsForSingleCustomerInMultipleMonthWithMultipleTransactions() {
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

       List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

        assertThat(customerPoints)
                .contains(
                       new CustomerPoint(1l, "2020-02",  140l),
                       new CustomerPoint(1l,"2020-03",  1l)
               );
    }

    @Test
    void calculatesPointsForMultipleCustomerInMultipleMonthWithMultipleTransactions() {
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

        List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

         assertThat(customerPoints)
                 .contains(
                         new CustomerPoint( 1l, "2020-02", 1l),
                         new CustomerPoint( 1l, "2020-03", 90l),
                         new CustomerPoint( 1l, "2020-04", 50l),
                         new CustomerPoint( 2l, "2020-02", 5l),
                         new CustomerPoint( 2l, "2020-03", 120l)
                 );
  }

    @Test
    void calculatesPointsForSingleCustomerInSingleMonthWithSingleTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        Transaction firstTransaction = new Transaction();
        firstTransaction.setCustomerId(1l);
        firstTransaction.setTransDate(LocalDate.of(2020, 2, 1));
        firstTransaction.setTransAmount(145.00);

        transactions.add(firstTransaction);

        List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

         assertThat(customerPoints).contains(
                new CustomerPoint(1l,"2020-02", 140l));
    }


    @Test
    void calculatesPointsForMultipleCustomerInMultipleMonthWithSingleTransactions() {
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

       List<CustomerPoint> customerPoints = pointsService.getPoints(transactions);

       assertThat(customerPoints)
                .contains(
                       new CustomerPoint(1l, "2020-02", 90l),
                       new CustomerPoint(2l, "2020-03", 1l)
                );
    }
}


