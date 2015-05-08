package org.craftedsw.harddependencies;

import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.user.User;

public class Given {

	private static final User LOGGEDIN_USER = anyUser();
	private static final User GUEST_USER = null;

	public static UserBuilder aCustomUser() {
		return new UserBuilder();
	}

	public static User anyUser() {
		return new User();
	}

	public static Trip anyTrip() {
		return new Trip();
	}

	public static User theLoggedInUser() {
		return LOGGEDIN_USER;
	}

	public static User theGuestUser() {
		return GUEST_USER;
	}

}
