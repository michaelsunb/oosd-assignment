/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.prototype.observer;

import chess.core.Piece;

public class PieceMovedEvent implements ChessEvent {
	private int oldPosition;
	private int newPosition;
	private Piece piece;
	
	public PieceMovedEvent(int oldPosition, int newPosition, Piece piece) {
		super();
		this.oldPosition = oldPosition;
		this.newPosition = newPosition;
		this.piece = piece;
	}
	
	public int getOldPosition() {
		return oldPosition;
	}
	
	public int getNewPosition() {
		return newPosition;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	@Override
	public String toString() {
		return "PieceMovedEvent [oldPosition=" + oldPosition + ", newPosition="
				+ newPosition + ", piece=" + piece + "]";
	}
	
}
