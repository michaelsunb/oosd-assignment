package chess.prototype.commands;

import chess.core.Board;
import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.observer.ChessEvent;

public class PieceCapturedCommand extends CommandBase implements CommandMoveDecision {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceMovedEvent)) return;
		if (!commandMoveDecision((PieceMovedEvent) event)) return;
		
		update((PieceMovedEvent) event);
	}

	@Override
	public void update(PieceMovedEvent event) {
		int newPos = event.getNewPosition();
		int oldPos = event.getPreviousPosition();

		Piece pieceSelected = board.getPiece(oldPos);
		Piece pieceEnemy = board.getPiece(newPos);

		if(pieceEnemy == null) return; // TODO

		Player playerSelected = pieceSelected.getOwner();

		playerSelected.addScore(pieceEnemy.getScore());
		board.setPiece(newPos, pieceSelected);
	}

	@Override
	public boolean commandMoveDecision(PieceMovedEvent event) {
		if(!isSelectedPieceValid(event)) return false;

		for(int i : allMovableSquares) {
			if (i == newPosition) {
				if(!((Board) board).isSqureEmpty(newPosition) &&
						targetOwner == null || selectedOwner != targetOwner) {
					return true;
				}
			}
		}

		return false;
	}
}
