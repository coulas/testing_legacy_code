package org.craftedsw.harddependencies;

import static org.assertj.core.api.Assertions.*;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

public class TripServiceTest {

	private final User GUEST = null;
	private final User SOLO_USER = new User();

	private User loggedUser = new User();

	private TripService testableTripService = new TripService() {
		@Override
		protected User getLoggedUser() {
			return loggedUser;
		}
	};

	@Test(expected = UserNotLoggedInException.class)
	public void shallThrowExceptionWhenNotLogged()
			throws UserNotLoggedInException {
		loggedUser = GUEST;
		try {
			testableTripService.getTripsByUser(SOLO_USER);
		} catch (UserNotLoggedInException e) {
			assertThat(e.getMessage()).isEqualTo("You need to log in in order to your friends trips.");
			throw e;
		}
	}
	
}
