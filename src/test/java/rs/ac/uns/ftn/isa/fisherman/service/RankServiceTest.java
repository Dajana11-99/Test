package rs.ac.uns.ftn.isa.fisherman.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rs.ac.uns.ftn.isa.fisherman.enums.RankType;
import rs.ac.uns.ftn.isa.fisherman.model.*;
import rs.ac.uns.ftn.isa.fisherman.repository.RankRepository;
import rs.ac.uns.ftn.isa.fisherman.service.impl.RankServiceImpl;

import java.util.Arrays;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankServiceTest {

    @Mock
    private RankRepository rankRepositoryMock;

    @InjectMocks
    private RankServiceImpl rankService;
    @Test
    public void testGetRankTypeByCurrentUserPoints() {

        BoatOwner user = new BoatOwner(1L, "Mila", "Milic", "mika@gmail.com", "123", "123456", null, "asasas");
        user.setUserRank(new UserRank(RankType.BRONZE, 150));

        when(rankRepositoryMock.findAll()).thenReturn(Arrays.asList(new Rank(1L, RankType.BRONZE, 0, 0), new Rank(2L, RankType.SILVER, 150, 5), new Rank(3L, RankType.GOLD, 250, 10)));
        when(rankRepositoryMock.getPointsByRank(RankType.SILVER.ordinal())).thenReturn(150);
        when(rankRepositoryMock.getPointsByRank(RankType.GOLD.ordinal())).thenReturn(250);

        RankType rankType = rankService.updateRankType(user.getUserRank().getCurrentPoints());

        assertThat(rankType).isNotEqualTo(RankType.BRONZE);
        assertThat(rankType).isEqualTo(RankType.SILVER);

    }

}
