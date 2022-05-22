package com.example.demo.mapper;


import com.example.demo.dto.ReservationsPointsDto;
import com.example.demo.model.ReservationPoints;

public class ReservationPointsMapper {
    public ReservationPoints dtoToReservationPoints(ReservationsPointsDto reservationPointsDto){
        return new ReservationPoints(reservationPointsDto.getId(),reservationPointsDto.getClientPoints(),
                reservationPointsDto.getOwnerPoints(),reservationPointsDto.getAppProfitPercentage(),reservationPointsDto.getCancelationFeePercentage());
    }
    public  ReservationsPointsDto reservationsPointsToDto(ReservationPoints reservationsPoints){
        return new ReservationsPointsDto(reservationsPoints.getId(),reservationsPoints.getClientPoints(),
                reservationsPoints.getOwnerPoints(),reservationsPoints.getAppProfitPercentage(),reservationsPoints.getCancelationFeePercentage());
    }
}
