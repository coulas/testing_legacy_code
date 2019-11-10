package org.craftedsw.harddependencies.trip;

import java.util.List;

import org.craftedsw.harddependencies.exception.DependendClassCallDuringUnitTestException;
import org.craftedsw.harddependencies.user.User;

public class TripDAO {

	/**
	 * @deprecated
	 * @see #findTripsFor(User)
	 **/
	@Deprecated
	public static List<Trip> findTripsByUser(User user) {
		throw new DependendClassCallDuringUnitTestException(
				"TripDAO should not be invoked on an unit test.");
	}
	
	public List<Trip> findTripsFor(User user) {
		return findTripsByUser(user);
	}
}
