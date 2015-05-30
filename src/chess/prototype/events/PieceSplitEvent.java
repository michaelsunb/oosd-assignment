<<<<<<< HEAD:src/chess/prototype/events/PieceSplitEvent.java
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
=======
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
>>>>>>> origin/stabilize-part-2:src/chess/mvc/models/PieceSplitEvent.java
