package org.craftedsw.harddependencies.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;

public class TripService {

	private final TripDAO tripDAO;

	public TripService(TripDAO tripDAO) {
		this.tripDAO = tripDAO;
	}

	public List<Trip> getTripsByUser(User user, User loggedUser)
			throws UserNotLoggedInException {
		validateUser(loggedUser);
		return user.isFriendWith(loggedUser)
				? findTripByUsers(user)
				: noTrips();
	}

	protected ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	private void validateUser(User loggedUser) throws UserNotLoggedInException {
		if (loggedUser == null) {
			throw new UserNotLoggedInException(
					"You need to log in in order to your friends trips.");
		}
	}

	protected List<Trip> findTripByUsers(User user) {
		return tripDAO.findByUser(user);
	}

}
