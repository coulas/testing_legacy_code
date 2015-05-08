package org.craftedsw.harddependencies;

import static org.assertj.core.api.Assertions.assertThat;

import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

public class UserTest {

	@Test
	public void shall_not_recognise_unkown_user() {
		User currentUser = Given.anyUser();
		User anotherUser = Given.anyUser();
		
		assertThat(currentUser.isFriendWith(anotherUser)).isFalse();
	}
	
	@Test
	public void shall_recognise_friends() {
		User anotherUser = 	Given.anyUser();
		User currentUser = Given.aCustomUser()
				.withFriends(anotherUser)
				.build();
		assertThat(currentUser.isFriendWith(anotherUser)).isTrue();
	}
}
