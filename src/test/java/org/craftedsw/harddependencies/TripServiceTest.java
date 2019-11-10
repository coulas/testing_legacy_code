package org.craftedsw.harddependencies;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.trip.TripDAO;
import org.craftedsw.harddependencies.trip.TripService;
import org.craftedsw.harddependencies.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class TripServiceTest {

    @Mock
    private TripDAO tripDao;

    @InjectMocks
    private TripService tripService;

    @Test
    public void should_throw_exception_when_not_logged_in() {
        assertThatThrownBy(
                () -> tripService.getTripsByUser(Given.anyUser(), Given.GUEST_USER))
                .isInstanceOf(UserNotLoggedInException.class);
    }

    @Test
    public void should_return_empty_when_stranger() throws UserNotLoggedInException {
        final User stranger = Given.aCustomUser()
                .withTrips(Given.TRIP_TO_BUDAPEST)
                .build();

        assertThat(tripService.getTripsByUser(stranger, Given.REGISTERED_USER))
                .isEmpty();
    }

    @Test
    public void should_return_trips_when_friends() throws UserNotLoggedInException {
        final Trip budapest = Given.TRIP_TO_BUDAPEST;
        final Trip london = Given.TRIP_TO_LONDON;
        final User friend = Given.aCustomUser()
                .withFriends(Given.anyUser(), Given.REGISTERED_USER)
                .withTrips(budapest, london)
                .build();
        given(tripDao.findTripsFor(friend)).willReturn(friend.getTrips());

        assertThat(tripService.getTripsByUser(friend, Given.REGISTERED_USER))
                .contains(london, budapest);
    }
}
