package org.craftedsw.harddependencies;

import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TripServiceTest {
	
	private User loggedInUser = Given.REGISTERED_USER;
	final TripService tripService = new TripService() {
		
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}
		
		@Override
		protected List<Trip> findTripsForUser(User user) {
			return user.trips();
		}
		
	};
	
	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_not_logged_in() throws Exception {
		loggedInUser = Given.GUEST_USER;
		
		tripService.getTripsByUser(Given.anyUser());
	}
	
	@Test
	public void should_return_empty_when_stranger() throws Exception {
		final User stranger = Given.aCustomUser()
				.withTrips(Given.TRIP_TO_BUDAPEST)
				.build();
		
		assertThat(tripService.getTripsByUser(stranger)).isEmpty();
	}
	
	@Test
	public void should_return_trips_when_friends() throws Exception {
		final Trip budapest = Given.TRIP_TO_BUDAPEST;
		final Trip london = Given.TRIP_TO_LONDON;
		final User friend = Given.aCustomUser()
				.withFriends(Given.anyUser(), Given.REGISTERED_USER)
				.withTrips(budapest, london)
				.build();
		
		assertThat(tripService.getTripsByUser(friend)).contains(london, budapest);
	}
}
