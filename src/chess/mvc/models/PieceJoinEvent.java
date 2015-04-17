/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.mvc.models;

import chess.core.Piece;
import chess.prototype.observer.ChessEvent;

public class PieceJoinEvent implements ChessEvent {
	private Piece currentPiece;
	private Piece augmentPiece;
	private int joinPosition;

	public PieceJoinEvent(Piece currentPiece, Piece augmentPiece, int joinPosition) {
		this.currentPiece = currentPiece;
		this.augmentPiece = augmentPiece;
		this.joinPosition = joinPosition;
	}

	public Piece getCurrentPiece() {
		return currentPiece;
	}

	public Piece getAugmentPiece() {
		return augmentPiece;
	}

	public int getJoinPosition() {
		return joinPosition;
	}

}
