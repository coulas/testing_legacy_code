package org.craftedsw.harddependencies.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.harddependencies.exception.UserNotLoggedInException;
import org.craftedsw.harddependencies.user.User;
import org.craftedsw.harddependencies.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		// not logged user throw exception and tripList is useless (avoid its init time and gc time)
		List<Trip> tripList = new ArrayList<Trip>();
		// test class without testing singleton
		User loggedUser = getLoggedUser();
		boolean isFriend = false;
		// logged or die is a safe guard (use every where?) and is not really a tripByUser logic a method call could be more explicit
		if (loggedUser != null) {
			// finding user friends is not a trip logic !
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break; 
				}
			}
			if (isFriend) {
				// test class without testing other dao (and static call)
				tripList = findTripByUsers(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException("You need to log in in order to your friends trips.");
		}
	}

	protected List<Trip> findTripByUsers(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}
}
