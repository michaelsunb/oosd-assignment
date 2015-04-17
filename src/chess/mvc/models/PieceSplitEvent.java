/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.mvc.models;

import chess.core.Piece;
import chess.prototype.observer.ChessEvent;

/*
 * Provide useful information about the piece needed to be split
 */
public class PieceSplitEvent implements ChessEvent {
	private Piece currentPiece;
	private Piece splitPiece;
	private int splitToPosition;
	private int splitFromPosition;
	
	public PieceSplitEvent(Piece currentPiece, Piece splitPiece,
			int splitToPosition, int splitFromPosition) {
		super();
		this.currentPiece = currentPiece;
		this.splitPiece = splitPiece;
		this.splitToPosition = splitToPosition;
		this.splitFromPosition = splitFromPosition;
	}

	public int getSplitPosition() {
		return splitFromPosition;
	}

	public Piece getCurrentPiece() {
		return currentPiece;
	}

	public Piece getSplitPiece() {
		return splitPiece;
	}

	public int getSplitToPosition() {
		return splitToPosition;
	}
}
