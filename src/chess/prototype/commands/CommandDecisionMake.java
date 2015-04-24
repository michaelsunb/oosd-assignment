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
	
	final private static ChessEvent PieceNotMovable = null;

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceCommandDecisionEvent)) return;

		if(commandDecision((PieceCommandDecisionEvent) event) != PieceNotMovable)
			eventMgr.fireEvent(commandDecision((PieceCommandDecisionEvent) event));
	}

	public ChessEvent commandDecision(PieceCommandDecisionEvent event) {
		int newPosition = event.getNewPosition();
		int oldPosition = event.getPreviousPosition();
		Piece piece = board.getPiece(oldPosition);
		
		// can't move empty piece
		if (piece == null) return PieceNotMovable;

		int[] allMovableSquares = piece.getMovablePositions(oldPosition);

		ChessEvent nextEvent = PieceNotMovable;
		
		for (int i : allMovableSquares) {
			if (i == newPosition) {
				// can't move empty piece
				if (piece == null)
					return PieceNotMovable;

				Player currOwner = piece.getOwner();
				if (currOwner == null)
					return PieceNotMovable; // previous piece has no owner (Barrier)

				// target location is empty
				if (((Board) board).isSqureEmpty(oldPosition)) {
					return PieceNotMovable;
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
