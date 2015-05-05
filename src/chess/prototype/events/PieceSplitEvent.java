/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.prototype.events;

import chess.core.Piece;

/*
 * Provide useful information about the piece needed to be split
 */
public class PieceSplitEvent implements ChessEvent {
	private Piece pieceToSplit;
	private int splitToPosition;
	private int currentPosition;

	public PieceSplitEvent(Piece pieceToSplit,
			int currentPosition, int splitToPosition) {
		super();
		this.pieceToSplit = pieceToSplit;
		this.currentPosition = currentPosition;
		this.splitToPosition = splitToPosition;
	}

	public Piece getPieceToSplit() {
		return pieceToSplit;
	}

	public void setPieceToSplit(Piece pieceToSplit) {
		this.pieceToSplit = pieceToSplit;
	}

	public int getSplitToPosition() {
		return splitToPosition;
	}

	public void setSplitToPosition(int splitToPosition) {
		this.splitToPosition = splitToPosition;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}
}
