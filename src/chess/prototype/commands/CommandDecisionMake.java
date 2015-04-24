/*
 * Author: Siang Ling
 * Number: s3295217
 */
package chess.prototype.commands;

import chess.core.Board;
import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceCapturedEvent;
import chess.mvc.models.PieceCommandDecisionEvent;
import chess.mvc.models.PieceJoinEvent;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.composite.Barrier;
import chess.prototype.observer.ChessEvent;

public class CommandDecisionMake extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceCommandDecisionEvent)) return;

		eventMgr.fireEvent(commandDecision((PieceCommandDecisionEvent) event));
	}

	public ChessEvent commandDecision(PieceCommandDecisionEvent event) {
		int newPosition = event.getNewPosition();
		int oldPosition = event.getPreviousPosition();
		Piece piece = board.getPiece(oldPosition);
		
		// can't move empty piece
		if (piece == null) return null;

		int[] allMovableSquares = piece.getMovablePositions(oldPosition);

		ChessEvent nextEvent = null;
		
		for (int i : allMovableSquares) {
			if (i == newPosition) {
				// can't move empty piece
				if (piece == null)
					return null;

				Player currOwner = piece.getOwner();
				if (currOwner == null)
					return null; // previous piece has no owner (Barrier)

				// target location is empty
				if (((Board) board).isSqureEmpty(oldPosition)) {
					return null;
				}
				
				nextEvent = new PieceMovedEvent(oldPosition,newPosition);
			
				if(board.getPiece(newPosition) == null) { // target position is empty
					return nextEvent;
				}

				// target location has no owner (Barrier)
				Player targetOwner = (board.getPiece(newPosition) != null) ? board
						.getPiece(newPosition).getOwner() : null;
				
				if (board.getPiece(newPosition).equals(Barrier.class))
				{
					nextEvent = new PieceCapturedEvent(oldPosition, newPosition);
				}
				else if (currOwner != targetOwner) // check if enemy
				{
					nextEvent = new PieceCapturedEvent(oldPosition, newPosition);
				}
				else if (currOwner == targetOwner) // check if friendly
				{
					nextEvent = new PieceJoinEvent(oldPosition, newPosition);
				}

			}
		}
		return nextEvent;
	}
}
