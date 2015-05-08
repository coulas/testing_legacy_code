package org.craftedsw.harddependencies;


import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;


public class TripServiceTest {
	TripService tripService = new TripService() {
		@Override
		protected User getLoggedInUser() {
			return null;
		}
	};
	@Test(expected = UserNotLoggedInException.class)
	public void shall_throw_exception_when_not_logged_in() throws UserNotLoggedInException {
		tripService.getTripsByUser(new User());
	}
	
}
