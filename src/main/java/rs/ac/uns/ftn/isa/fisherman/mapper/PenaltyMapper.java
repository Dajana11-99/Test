package rs.ac.uns.ftn.isa.fisherman.mapper;

import rs.ac.uns.ftn.isa.fisherman.dto.PenaltyDto;
import rs.ac.uns.ftn.isa.fisherman.model.Penalty;

import java.util.HashSet;
import java.util.Set;

public class PenaltyMapper {

    public Set<PenaltyDto> penaltiesToPenaltiesDto(Set<Penalty> penalties){
        Set<PenaltyDto> penaltiesDto = new HashSet<>();
        for(Penalty penalty : penalties){
            penaltiesDto.add(new PenaltyDto(penalty.getId(), penalty.getDate()));
        }
        return penaltiesDto;
    }

}
