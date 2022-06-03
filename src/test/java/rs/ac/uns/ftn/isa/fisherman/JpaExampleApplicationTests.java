package rs.ac.uns.ftn.isa.fisherman;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import rs.ac.uns.ftn.isa.fisherman.controller.*;
import rs.ac.uns.ftn.isa.fisherman.service.*;

//@RunWith(SpringRunner.class)
@RunWith(Suite.class)
@Suite.SuiteClasses({
		AccountControllerTest.class,
		AdventureControllerTest.class,
		AdventureQuickReservationControllerTest.class,
		AdventureReservationControllerTest.class,
		AuthenticationControllerTest.class,
		AvailablePeriodBoatControllerTest.class,
		BoatReservationControllerTest.class,
		CabinControllerTest.class,
		CabinQuickReservationControllerTest.class,
		CabinStatisticsReportControllerTest.class,
		ComplaintControllerTest.class,
		FishingInstructorControllerTest.class,
		AdventureReservationCancellationServiceTest.class,
		AdventureReservationServiceTest.class,
		AdventureServiceTest.class,
		AdventureSubscriptionServiceTest.class,
		BoatReservationServiceTest.class,
		BoatServiceTest.class,
		CabinAvailablePeriodServiceTest.class,
		CabinReservationServiceTest.class,
		CabinServiceTest.class,
		OwnersReportServiceTest.class,
		PaymentServiceTest.class,
		PenaltyServiceTest.class,
		QuickReservationBoatServiceTest.class,
		RankServiceTest.class,
		UserServiceTest.class
})
@SpringBootTest
public class JpaExampleApplicationTests
{

	@Test
	public void contextLoads()
	{
	}

}
