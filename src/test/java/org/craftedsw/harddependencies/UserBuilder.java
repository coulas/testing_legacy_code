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
		addTrips(current);
		addFriends(current);
		return current;
	}

	protected void addFriends(User current) {
		if (users != null) {
			for (User user : users) {
				current.addFriend(user);
			}
		}
	}

	protected void addTrips(User current) {
		if (trips != null) {
			for (Trip trip : trips) {
				current.addTrip(trip);
			}
		}
	}
}
