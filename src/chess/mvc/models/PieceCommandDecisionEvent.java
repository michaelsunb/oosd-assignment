package chess.mvc.models;

import chess.prototype.observer.ChessEvent;

public class PieceCommandDecisionEvent implements ChessEvent {
	private int newPosition;
	private int previousPosition;

	public PieceCommandDecisionEvent(int previousPosition, int newPosition) {
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
