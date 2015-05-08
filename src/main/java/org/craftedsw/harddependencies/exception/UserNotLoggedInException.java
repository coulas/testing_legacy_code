package org.craftedsw.harddependencies.exception;

public class UserNotLoggedInException extends Exception {

	public UserNotLoggedInException() {
		
	}
	
	public UserNotLoggedInException(String businesMessage) {
		super(businesMessage);
	}

	private static final long serialVersionUID = 8959479918185637340L;

}
