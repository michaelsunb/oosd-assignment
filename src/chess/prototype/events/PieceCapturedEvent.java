<<<<<<< HEAD:src/chess/prototype/events/PieceCapturedEvent.java
/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.prototype.events;


public class PieceCapturedEvent implements ChessEvent {
	private int newPosition;
	private int previousPosition;

	public PieceCapturedEvent(int previousPosition, int newPosition) {
		super();
		this.newPosition = newPosition;
		this.previousPosition = previousPosition;
	}

	public int getNewPosition() {
		return newPosition;
	}

	public int getPreviousPosition() {
		return previousPosition;
	}
}
=======
/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.mvc.models;

import chess.prototype.observer.ChessEvent;

public class PieceCapturedEvent implements ChessEvent {
	private int newPosition;
	private int previousPosition;

	public PieceCapturedEvent(int previousPosition, int newPosition) {
		super();
		this.newPosition = newPosition;
		this.previousPosition = previousPosition;
	}

	public int getNewPosition() {
		return newPosition;
	}

	public int getPreviousPosition() {
		return previousPosition;
	}
}
>>>>>>> origin/stabilize-part-2:src/chess/mvc/models/PieceCapturedEvent.java
