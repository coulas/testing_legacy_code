package org.craftedsw.harddependencies;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Before;
import org.junit.Test;

public class TripServiceTest {

	private User loggedUser;

	private TripService testableTripService;

	@Before
	public void commonDefaults() {
		testableTripService = new TripService() {
			@Override
			protected User getLoggedUser() {
				return loggedUser;
			}

			@Override
			protected List<Trip> findTripByUsers(User user) {
				return user.trips();
			}
		};

		loggedUser = Given.theRegisteredUser();
	}

	@Test(expected = UserNotLoggedInException.class)
	public void shallThrowExceptionWhenNotLogged()
			throws UserNotLoggedInException {
		loggedUser = Given.theGuestUser();
		try {
			testableTripService.getTripsByUser(Given.anyNewUser());
		} catch (UserNotLoggedInException e) {
			assertThat(e.getMessage()).isEqualTo(
					"You need to log in in order to your friends trips.");
			throw e;
		}
	}

	@Test
	public void shall_return_empty_list_when_not_friends()
			throws UserNotLoggedInException {
		User stranger = Given.anyCustomUser()
			.withFriends(Given.anyNewUser())
			.withTrips(new Trip())
			.build();

		List<Trip> trips = testableTripService.getTripsByUser(stranger);

		assertThat(trips).isEmpty();
	}

	@Test
	public void shall_return_trips_when_friends()
			throws UserNotLoggedInException {
		Trip budapest = Given.tripToBudapest();
		Trip london = Given.tripToLondon();
		User friend = Given.anyCustomUser()
				.withFriends(Given.theRegisteredUser(), Given.anyNewUser())
				.withTrips(budapest, london)
				.build();

		List<Trip> trips = testableTripService.getTripsByUser(friend);

		assertThat(trips).containsOnly(london, budapest);
	}
}
