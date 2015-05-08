package org.craftedsw.harddependencies.user;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.harddependencies.trip.Trip;

public class User {

	private List<Trip> trips = new ArrayList<Trip>();
	private List<User> friends = new ArrayList<User>();

	public List<User> getFriends() {
		return friends;
	}

	public void addFriend(User user) {
		friends.add(user);
	}

	public void addTrip(Trip trip) {
		trips.add(trip);
	}

	public List<Trip> trips() {
		return trips;
	}

	// User is not an isolated helper class, and this method WILL be used by
	// another code, the only question is when.
	// So this "emerged", but without testing is legacy.
	public boolean isFriendWith(User loggedUser) {
		boolean isFriend = false;
		for (User friend : getFriends()) {
			if (friend.equals(loggedUser)) {
				isFriend = true;
				break;
			}
		}
		return isFriend;
	}
}
