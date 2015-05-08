package org.craftedsw.harddependencies;

import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.user.User;

public class Given {
	private static final User GUEST = null;
	private static final User REGISTERED_USER = new User();
	private static final Trip BUDAPEST = new Trip();
	private static final Trip LONDON = new Trip();


	public static UserBuilder anyCustomUser() {
		return new UserBuilder();
	}
	public static User theRegisteredUser() {
		return REGISTERED_USER;
	}
	public static User theGuestUser() {
		return GUEST;
	}
	public static User anyNewUser() {
		return new User();
	}
	public static Trip tripToBudapest() {
		return BUDAPEST;
	}
	public static Trip tripToLondon() {
		return LONDON;
	}
}

