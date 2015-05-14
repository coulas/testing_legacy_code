package org.craftedsw.harddependencies;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

public class TripServiceTest {
	final TripService tripService = new TripService();
	
	@Test(expected = UserNotLoggedInException.class)
	public void should_throw_exception_when_not_logged_in() throws Exception {
		final User anyUser = new User();
		tripService.getTripsByUser(anyUser);
	}
}
