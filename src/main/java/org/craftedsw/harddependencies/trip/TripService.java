package org.craftedsw.harddependencies.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;
import org.craftedsw.harddependencies.user.UserSession;

public class TripService {
	
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedInUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		boolean isFriend = false;
		for (User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				isFriend = true;
				break;
			}
		}
		List<Trip> tripList = new ArrayList<Trip>();
		if (isFriend) {
			tripList = findTripsForUser(user);
		}
		return tripList;
	}
	
	protected List<Trip> findTripsForUser(User user) {
		return TripDAO.findTripsByUser(user);
	}
	
	protected User getLoggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}
}
