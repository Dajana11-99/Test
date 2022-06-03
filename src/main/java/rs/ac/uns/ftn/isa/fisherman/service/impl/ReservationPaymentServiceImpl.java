package rs.ac.uns.ftn.isa.fisherman.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isa.fisherman.service.UserService;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.service.RankService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationPaymentService;
import rs.ac.uns.ftn.isa.fisherman.service.ReservationPointsService;

import java.text.DecimalFormat;

@Service
public class ReservationPaymentServiceImpl implements ReservationPaymentService {
    @Autowired
    private ReservationPointsService reservationPointsService;
    @Autowired
    private RankService rankService;
    @Autowired
    private UserService userService;


    @Override
    public PaymentInformation setTotalPaymentAmount(Reservation reservation,User user){
        PaymentInformation paymentInformation = new PaymentInformation();
        Double newTotalPrice = getClientsTotalPrice(reservation.getClient(),reservation.getPaymentInformation().getTotalPrice());
        paymentInformation.setTotalPrice(newTotalPrice);
        Double ownersPart= calculateOwnersPart(newTotalPrice,user);
        paymentInformation.setOwnersPart(ownersPart);
        paymentInformation.setCompanysPart(newTotalPrice-ownersPart);
        return  paymentInformation;
    }

    private Double calculateOwnersPart(Double newTotalPrice, User user) {
        ReservationPoints points = reservationPointsService.get();
        Integer instructorsDiscount= rankService.getDiscountByRank(user.getUserRank().getRankType().ordinal());
        Integer appPercentage= points.getAppProfitPercentage();
       return calculateOwnerPartOfReservationPrice(appPercentage,newTotalPrice,instructorsDiscount);
    }

    @Override
    public PaymentInformation setTotalPaymentAmountForQuickAction (Reservation reservation,User user,int discount) {

       double totalPriceWithDiscount= reservation.getPaymentInformation().getTotalPrice()-(discount/100.0 * reservation.getPaymentInformation().getTotalPrice());
        PaymentInformation paymentInformation = new PaymentInformation();
        Double newTotalPrice = getClientsTotalPrice(reservation.getClient(),totalPriceWithDiscount);
        paymentInformation.setTotalPrice(newTotalPrice);
        Double ownersPart= calculateOwnersPart(newTotalPrice,user);
        paymentInformation.setOwnersPart(ownersPart);
        paymentInformation.setCompanysPart(newTotalPrice-ownersPart);
        return  paymentInformation;
    }

    public Double getClientsTotalPrice(Client client, Double totalPrice) {
        Integer clientDiscount= rankService.getDiscountByRank(client.getUserRank().getRankType().ordinal());
        return  calculateClinetsDiscount(clientDiscount,totalPrice);
    }

    public Double calculateClinetsDiscount(Integer clientDiscount,Double totalPrice){
        if(clientDiscount==0)
            return  totalPrice;
        return totalPrice-clientDiscount/100.00 * totalPrice;
    }
    public Double calculateOwnerPartOfReservationPrice(Integer appPercentage,Double newTotalPrice,Integer instructorsDiscount){
        Double appsPartWithoutDiscount = appPercentage/100.00 * newTotalPrice;
        if(instructorsDiscount==0)
            return newTotalPrice-appsPartWithoutDiscount;
        return  newTotalPrice -(appsPartWithoutDiscount-instructorsDiscount/100.00* appsPartWithoutDiscount);
    }
    @Override
    public void updateUserRankAfterReservation(Client client, User user) {
        ReservationPoints points = reservationPointsService.get();
        Integer clientPoints= client.getUserRank().getCurrentPoints()+points.getClientPoints();
        Integer userPoints = user.getUserRank().getCurrentPoints()+points.getOwnerPoints();
        user.getUserRank().setCurrentPoints(userPoints);
        client.getUserRank().setCurrentPoints(clientPoints);
        client.getUserRank().setRankType(rankService.updateRankType(clientPoints));
        user.getUserRank().setRankType(rankService.updateRankType(userPoints));
        userService.save(client);
        userService.save(user);
    }

    @Override
    public void resetLoyaltyStatusAfterCancellation(Client client, User user) {
        ReservationPoints points = reservationPointsService.get();
        Integer clientPoints=client.getUserRank().getCurrentPoints()-points.getClientPoints();
        Integer userPoints = user.getUserRank().getCurrentPoints()-points.getOwnerPoints();
        
        client.getUserRank().setCurrentPoints(clientPoints);
        user.getUserRank().setCurrentPoints(userPoints);

        client.getUserRank().setRankType(rankService.updateRankType(clientPoints));
        user.getUserRank().setRankType(rankService.updateRankType(userPoints));
        userService.save(client);
        userService.save(user);

    }


}
