package org.craftedsw.harddependencies.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;
import org.craftedsw.harddependencies.user.UserSession;

public class TripService {
	
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedInUser = validateLoggedInUser();
		return user.isFriendWith(loggedInUser)
				? findTripsForUser(user)
				: noTrips();
	}

	private User validateLoggedInUser() throws UserNotLoggedInException {
		User loggedInUser = getLoggedInUser();
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}
		return loggedInUser;
	}

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}
	
	protected List<Trip> findTripsForUser(User user) {
		return TripDAO.findTripsByUser(user);
	}
	
	protected User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}
}
