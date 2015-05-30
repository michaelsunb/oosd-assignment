package chess.mvc.models;

import chess.prototype.observer.ChessEvent;

public class UndoEvent implements ChessEvent {
	int numOfReverts;

	public UndoEvent(int num) {
		this.numOfReverts = num;
	}
	
	public final int getNumOfReverts() {
		return numOfReverts;
	}
}
