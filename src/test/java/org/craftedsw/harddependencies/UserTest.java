package org.craftedsw.harddependencies;

import static org.assertj.core.api.Assertions.*;

import org.craftedsw.harddependencies.user.User;
import org.junit.Test;

public class UserTest {

	@Test
	public void should_not_recognise_strangers() {
		User anyNewUser = Given.anyNewUser();
		User anotherUser = Given.anyNewUser();

		assertThat(anyNewUser.isFriendWith(anotherUser)).isFalse();
	}

	@Test
	public void should_recognise_friends() {
		User anotherUser = Given.anyNewUser();
		User anyNewUser = Given.anyCustomUser()
				.withFriends(anotherUser)
				.build();

		assertThat(anyNewUser.isFriendWith(anotherUser)).isTrue();
	}
}
