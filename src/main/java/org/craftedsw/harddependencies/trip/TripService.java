package org.craftedsw.harddependencies.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;

public class TripService {

	private final TripDAO tripDAO;
	
	public TripService(TripDAO tripDAO) {
		super();
		this.tripDAO = tripDAO;
	}

	public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
		validate(loggedInUser);
		return user.isFriendWith(loggedInUser)
				? findTripsByUser(user)
				: noTrips();
	}

	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}

	private void validate(User loggedInUser) throws UserNotLoggedInException {
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}
	}

	protected List<Trip> findTripsByUser(User user) {
		return tripDAO.findByUser(user);
	}

}
