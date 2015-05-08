package org.craftedsw.harddependencies;

import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.user.User;

public class UserBuilder {
	private User[] users;
	private Trip[] trips;

	public UserBuilder withFriends(User... users) {
		this.users = users;
		return this;
	}

	public UserBuilder withTrips(Trip... trips) {
		this.trips = trips;
		return this;
	}

	public User build() {
		User current = new User();
		for (Trip trip : trips) {
			current.addTrip(trip);
		}
		for (User user : users) {
			current.addFriend(user);
		}
		return current;
	}
}
