package org.craftedsw.harddependencies;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

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
	private TripService testableTripService;

	@Before
	public void commonDefaults() {

		testableTripService = new TripService(tripDao);
	}

	@Test(expected = UserNotLoggedInException.class)
	public void shallThrowExceptionWhenNotLogged()
			throws UserNotLoggedInException {
		User currentUser = Given.theGuestUser();
		try {
			testableTripService.getTripsByUser(Given.anyNewUser(), currentUser);
		} catch (UserNotLoggedInException e) {
			assertThat(e.getMessage()).isEqualTo(
					"You need to log in in order to your friends trips.");
			throw e;
		}
	}

	@Test
	public void shall_return_empty_list_when_not_friends()
			throws UserNotLoggedInException {
		User currentUser = Given.theRegisteredUser();
		User stranger = Given.anyCustomUser().withFriends(Given.anyNewUser())
				.withTrips(new Trip()).build();

		List<Trip> trips = testableTripService.getTripsByUser(stranger,
				currentUser);

		assertThat(trips).isEmpty();
	}

	@Test
	public void shall_return_trips_when_friends()
			throws UserNotLoggedInException {
		User currentUser = Given.theRegisteredUser();
		Trip budapest = Given.tripToBudapest();
		Trip london = Given.tripToLondon();
		User friend = Given.anyCustomUser()
				.withFriends(Given.theRegisteredUser(), Given.anyNewUser())
				.withTrips(budapest, london).build();
		given(tripDao.findByUser(friend)).willReturn(friend.trips());

		List<Trip> trips = testableTripService.getTripsByUser(friend,
				currentUser);

		assertThat(trips).containsOnly(london, budapest);
	}
}
