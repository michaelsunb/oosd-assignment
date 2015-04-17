/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.mvc.models;

import chess.core.Piece;
import chess.core.Player;
import chess.prototype.observer.ChessEvent;

public class PieceCapturedEvent implements ChessEvent {
	private Player captureBy;
	private Piece capturedPiece;
	private int capturedPosition;

	public PieceCapturedEvent(Player captureBy, Piece capturedPiece,
			int capturedPosition) {
		this.captureBy = captureBy;
		this.capturedPiece = capturedPiece;
		this.capturedPosition = capturedPosition;
	}

	public Player getCaptureBy() {
		return captureBy;
	}

	public Piece getCapturedPiece() {
		return capturedPiece;
	}

	public int getCapturedPosition() {
		return capturedPosition;
	}

}
