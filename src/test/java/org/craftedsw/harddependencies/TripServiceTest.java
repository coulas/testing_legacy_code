package org.craftedsw.harddependencies;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

public class TripServiceTest {

	private final User GUEST = null;
	private final User REGISTERED_USER = new User();
	private final Trip BUDAPEST = new Trip();
	private final Trip LONDON = new Trip();

	private User loggedUser;

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
			testableTripService.getTripsByUser(new User());
		} catch (UserNotLoggedInException e) {
			assertThat(e.getMessage()).isEqualTo(
					"You need to log in in order to your friends trips.");
			throw e;
		}
	}

	@Test
	public void shall_return_empty_list_when_not_friends()
			throws UserNotLoggedInException {
		loggedUser = REGISTERED_USER;
		User stranger = new User();
		stranger.addFriend(new User());
		stranger.addTrip(new Trip());

		List<Trip> trips = testableTripService.getTripsByUser(stranger);

		assertThat(trips).isEmpty();
	}

	@Test
	public void shall_return_Trips_when_friends()
			throws UserNotLoggedInException {
		loggedUser = REGISTERED_USER;
		User friend = new User();
		friend.addFriend(REGISTERED_USER);
		friend.addFriend(new User());
		friend.addTrip(BUDAPEST);
		friend.addTrip(LONDON);
		
		List<Trip> trips = testableTripService.getTripsByUser(friend);

		assertThat(trips).containsOnly(LONDON, BUDAPEST);
	}
}
