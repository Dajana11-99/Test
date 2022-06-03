package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.PenaltyDto;
import rs.ac.uns.ftn.isa.fisherman.model.Penalty;

import java.util.ArrayList;
import java.util.List;

public class PenaltyMapper {

    public List<PenaltyDto> penaltiesToPenaltiesDto(List<Penalty> penalties){
        List<PenaltyDto> penaltiesDto = new ArrayList<>();
        for(Penalty penalty : penalties){
            penaltiesDto.add(new PenaltyDto(penalty.getId(), penalty.getDate()));
        }
        return penaltiesDto;
    }

}
