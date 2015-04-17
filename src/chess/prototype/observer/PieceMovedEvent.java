/*
 * Author: Sokun CHORN
 * Student Number: s3338291
 */
package chess.prototype.observer;

import chess.core.Board;
import chess.core.Game;
import chess.core.Piece;
import chess.core.Player;
import chess.prototype.composite.Barrier;

public class PieceMovedEvent implements ChessEvent {
	private int newPosition;
	private Piece previousPiece;

	public PieceMovedEvent(int newPosition) {
		super();
		this.newPosition = newPosition;
	}
	
	public int getNewPosition() {
		return newPosition;
	}
	
	public final Piece getPreviousPosition() {
		return previousPiece;
	}

	public final void setPreviousPiece(Piece previousPiece) {
		this.previousPiece = previousPiece;
	}
	
	public ChessEvent returnAction() {
		Game game = Game.getInstance();
		Board board = (Board) game.getBoardInstance();

		// can't move empty piece
		if(this.previousPiece == null) return null;
		
		Player currOwner = previousPiece.getOwner();
		if(currOwner == null) return null; // previous piece has no owner (Barrier)
		
		// target location is empty
		if(board.isSqureEmpty(newPosition)){
			return this;
		}
		
		// target location has no owner (Barrier)
		Player targetOwner = (board.getPiece(newPosition) != null) ? board.getPiece(newPosition).getOwner() : null;
		if(board.getPiece(newPosition).equals(Barrier.class)) {
			Piece barrier = board.getPiece(newPosition);
			return new PieceCapturedEvent(currOwner, barrier, newPosition);
		}

		// check if enemy
		if(currOwner != targetOwner) {
			Piece capturedPiece = previousPiece;
        	return new PieceCapturedEvent(currOwner,capturedPiece,newPosition);
		}

		// check if friendly
		if(currOwner == targetOwner) {
			Piece capturedPiece = board.getPiece(newPosition);
			return new PieceJoinEvent(previousPiece,capturedPiece,newPosition);
		}

		return this;
	}
}
