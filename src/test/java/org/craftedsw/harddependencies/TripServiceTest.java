package org.craftedsw.harddependencies;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

public class TripServiceTest {

	private final User GUEST = null;
	private final User SOLO_USER = new User();
	
	private User loggedUser = new User();
	
	TripService tripService = new TripService() {
		@Override
		protected User getLoggedUser() {
			return loggedUser;
		}
	};
	
	@Test(expected=UserNotLoggedInException.class)
	public void shallThrowExceptionWhenNotLogged() throws UserNotLoggedInException {
		loggedUser = GUEST;
		tripService.getTripsByUser(SOLO_USER);
	}
}
