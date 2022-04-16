package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.ReservationsPointsDto;
import rs.ac.uns.ftn.isa.fisherman.model.ReservationPoints;

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
