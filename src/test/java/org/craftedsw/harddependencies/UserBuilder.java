package org.craftedsw.harddependencies;

import org.craftedsw.harddependencies.trip.Trip;
import org.craftedsw.harddependencies.user.User;

public class UserBuilder {

	private User[] friends;
	private Trip[] trips;

	public UserBuilder withFriends(User... friends) {
		this.friends = friends;
		return this;
	}

	public UserBuilder withTrips(Trip... trips) {
		this.trips = trips;
		return this;
	}

	public User build() {
		User user = new User();
		addFriends(user);
		addTrips(user);
		return user;
	}

	private void addFriends(User user) {
		if (friends != null) {
			for (User friend : friends) {
				user.addFriend(friend);
			}
		}
	}

	private void addTrips(User user) {
		if (trips != null) {
			for (Trip trip : trips) {
				user.addTrip(trip);
			}
		}
	}

}
