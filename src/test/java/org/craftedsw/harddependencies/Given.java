package org.craftedsw.harddependencies;

import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.user.User;

public class Given {
	public static final User GUEST_USER = null;
	public static final User REGISTERED_USER = new User();
	public static final Trip TRIP_TO_BUDAPEST = new Trip();
	public static final Trip TRIP_TO_LONDON = new Trip();
	
	public static UserBuilder aCustomUser() {
		return new UserBuilder();
	}
	
	public static User anyUser() {
		return new User();
	}
	
}
