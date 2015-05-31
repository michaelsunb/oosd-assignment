package chess.mvc.models;

import chess.prototype.observer.ChessEvent;
/*
 * Just a Subject so that observer can subscribe to
 */
public class UpdateUIEvent implements ChessEvent {
	private final boolean renew;
	
	public UpdateUIEvent() {
		this.renew = false;
	}
	
	/**
	 * @pre.condition: input of true/false
	 * @post.condition: Instantiate class and will renew 
	 * the board if called with input value of true
	 */
	public UpdateUIEvent(boolean renew) {
		this.renew = renew;
	}

	public boolean isRenew() {
		return renew;
	}
}
