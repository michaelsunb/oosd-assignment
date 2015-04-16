/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.prototype.observer;

public class PieceMovedEvent implements ChessEvent {
	private int newPosition;
	
	public PieceMovedEvent(int newPosition) {
		super();
		this.newPosition = newPosition;
	}
	
	public int getNewPosition() {
		return newPosition;
	}
}
