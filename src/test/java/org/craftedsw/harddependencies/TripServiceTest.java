package org.craftedsw.harddependencies;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TripServiceTest {
	
	private static final User GUEST_USER = null;
	private static final User REGISTERED_USER = new User();
	private User loggedInUser = REGISTERED_USER;
	final TripService tripService = new TripService() {
		
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}
		
	};
	
	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_not_logged_in() throws Exception {
		loggedInUser = GUEST_USER;
		final User anyUser = new User();
		tripService.getTripsByUser(anyUser);
	}
	
	@Test
	public void should_return_empty_when_stranger() throws Exception {
		final User stranger = new User();
		final Trip budapest = new Trip();
		stranger.addTrip(budapest);
		assertThat(tripService.getTripsByUser(stranger)).isEmpty();
	}
}
