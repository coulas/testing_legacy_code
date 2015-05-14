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
		validateLoggedInUser(loggedInUser);
		return user.isFriendWith(loggedInUser)
				? findTripsForUser(user)
				: noTrips();
	}
	
	private void validateLoggedInUser(User loggedInUser) throws UserNotLoggedInException {
		if (loggedInUser == null) {
			throw new UserNotLoggedInException();
		}
	}
	
	private ArrayList<Trip> noTrips() {
		return new ArrayList<Trip>();
	}
	
	protected List<Trip> findTripsForUser(User user) {
		return tripDAO.findTripsFor(user);
	}
	
}
