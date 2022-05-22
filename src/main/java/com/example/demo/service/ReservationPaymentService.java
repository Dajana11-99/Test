package com.example.demo.service;


import com.example.demo.model.Client;
import com.example.demo.model.PaymentInformation;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;

public interface ReservationPaymentService {
    PaymentInformation setTotalPaymentAmount(Reservation reservation, User user);
    void updateUserRankAfterReservation(Client client, User user);
    PaymentInformation setTotalPaymentAmountForQuickAction (Reservation reservation,User user,int discount);
    void resetLoyaltyStatusAfterCancellation(Client client, User user);
}
