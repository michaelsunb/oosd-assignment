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
