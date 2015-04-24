package chess.prototype.commands;

import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceCapturedEvent;
import chess.prototype.observer.ChessEvent;

public class PieceCapturedCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceCapturedEvent)) return;
		
		PieceCaptured((PieceCapturedEvent) event);
	}

	public void PieceCaptured(PieceCapturedEvent event) {
		int newPos = event.getNewPosition();
		int oldPos = event.getPreviousPosition();

		Piece pieceSelected = board.getPiece(oldPos);
		Piece pieceEnemy = board.getPiece(newPos);

		Player playerSelected = pieceSelected.getOwner();

		playerSelected.addScore(pieceEnemy.getScore());
		board.setPiece(newPos, pieceSelected);
	}
}
