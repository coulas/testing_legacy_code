package org.craftedsw.harddependencies.user;

import org.craftedsw.harddependencies.Given;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
	@Test
	public void should_not_know_strangers() throws Exception {
		assertThat(Given.anyUser().isFriendWith(Given.anyUser())).isFalse();
	}
	
	@Test
	public void should_know_friends() throws Exception {
		final User anotherUser = Given.anyUser();
		final User anyUser = Given.aCustomUser()
				.withFriends(anotherUser)
				.build();
		assertThat(anyUser.isFriendWith(anotherUser)).isTrue();
	}
}
