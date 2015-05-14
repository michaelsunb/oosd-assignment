/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.mvc.models;

import chess.core.Piece;
import chess.prototype.composite.CombinePiece;
import chess.prototype.observer.ChessEvent;

/*
 * Provide useful information about the piece needed to be split
 */
public class PieceSplitEvent implements ChessEvent {
	private CombinePiece currentPiece;
	private Piece splitPiece;
	
	private int newPos;

	public PieceSplitEvent(CombinePiece mainPiece, Piece splitPiece,
			int splitToPosition) {
		this.currentPiece = mainPiece;
		this.splitPiece = splitPiece;
		this.newPos = splitToPosition;
	}

	public CombinePiece getCurrentPiece() {
		return currentPiece;
	}

	public Piece getSplitPiece() {
		return splitPiece;
	}

	public int getSplitToPosition() {
		return newPos;
	}
}
