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
import rs.ac.uns.ftn.isa.fisherman.service.impl.ReservationPaymentServiceImpl;
import java.util.*;

import static org.mockito.Mockito.when;



@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentServiceTest {

    @Mock
    private RankRepository rankRepositoryMock;

    @InjectMocks
    private ReservationPaymentServiceImpl paymentService;

    @InjectMocks
    private RankServiceImpl rankService;

    @Test
    public void testCalculatePriceWithDiscountForClient() {

        Double totalPriceForReservation =500.00;
        Client client = new Client(1L, "Mila", "Milic", "mika@gmail.com", "123", "123456", null);
        client.setUserRank(new UserRank(RankType.GOLD, 300));

        when(rankRepositoryMock.findAll()).thenReturn(Arrays.asList(new Rank(1L, RankType.BRONZE, 0, 0), new Rank(2L, RankType.SILVER, 150, 5), new Rank(3L, RankType.GOLD, 250, 10)));
        when(rankRepositoryMock.getDiscountByRank(client.getUserRank().getRankType().ordinal())).thenReturn(10);

        Integer clientDiscount = rankService.getDiscountByRank(client.getUserRank().getRankType().ordinal());
         Double priceResult = paymentService.calculateClinetsDiscount(clientDiscount,totalPriceForReservation);

        assertThat(priceResult).isEqualTo(450.00);
        assertThat(priceResult).isLessThan(totalPriceForReservation);
    }

    @Test
    public void testcalculateOwnerPartOfReservationPrice() {

        FishingInstructor fishingInstructor = new FishingInstructor(1L, "Mila", "Milic", "mika@gmail.com", "123", "123456",null,null,null,"dasdasd");
        fishingInstructor.setUserRank(new UserRank(RankType.GOLD, 300));

        when(rankRepositoryMock.findAll()).thenReturn(Arrays.asList(new Rank(1L, RankType.BRONZE, 0, 0), new Rank(2L, RankType.SILVER, 150, 5), new Rank(3L, RankType.GOLD, 250, 10)));
        when(rankRepositoryMock.getDiscountByRank(fishingInstructor.getUserRank().getRankType().ordinal())).thenReturn(10);

        Integer instructorDiscount = rankService.getDiscountByRank(fishingInstructor.getUserRank().getRankType().ordinal());
        Double priceResult = paymentService.calculateOwnerPartOfReservationPrice(2,800.00,instructorDiscount);

        assertThat(priceResult).isEqualTo(785.6);
        assertThat(instructorDiscount).isNotZero();
        assertThat(instructorDiscount).isGreaterThan(5);

    }
}
