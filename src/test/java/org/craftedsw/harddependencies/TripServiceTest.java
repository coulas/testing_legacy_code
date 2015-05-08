package org.craftedsw.harddependencies;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.trip.TripDAO;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {
	
	@Mock
	private TripDAO tripDao;
	
	@InjectMocks
	private TripService tripService;
	
	@Before
	public void configureMocks() {
		tripService = new TripService(tripDao);
	}
	
	@Test(expected = UserNotLoggedInException.class)
	public void shall_throw_exception_when_not_logged_in() throws UserNotLoggedInException {
		tripService.getTripsByUser(new User(), Given.theGuestUser());
	}
	
	@Test
	public void shall_have_no_trips_when_not_friends() throws UserNotLoggedInException {
		User stranger = Given.aCustomUser()
				.withFriends(Given.anyUser())
				.withTrips(Given.anyTrip())
				.build();
		
		assertThat(tripService.getTripsByUser(stranger, Given.theLoggedInUser())).isEmpty();
	}

	@Test
	public void shall_have_trips_when_friends() throws UserNotLoggedInException {
		Trip budapest = Given.anyTrip();
		Trip londre = Given.anyTrip();
		User friend = Given.aCustomUser()
				.withFriends(Given.anyUser(), Given.theLoggedInUser())
				.withTrips(budapest, londre)
				.build();
		given(tripDao.findByUser(friend)).willReturn(friend.trips());
		
		assertThat(tripService.getTripsByUser(friend, Given.theLoggedInUser())).contains(londre, budapest);
	}
}
