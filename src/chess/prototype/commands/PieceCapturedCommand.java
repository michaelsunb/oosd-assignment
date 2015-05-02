package chess.prototype.commands;

import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceCapturedEvent;
import chess.prototype.observer.ChessEvent;

public class PieceCapturedCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceCapturedEvent)) return;
		
		capture((PieceCapturedEvent) event);
	}

	public void capture(PieceCapturedEvent event) {
		int newPos = event.getNewPosition();
		int oldPos = event.getPreviousPosition();

		Piece pieceSelected = this.getBoard().getPiece(oldPos);
		Piece pieceEnemy = this.getBoard().getPiece(newPos);

		if(pieceEnemy == null) return; // TODO

		Player playerSelected = pieceSelected.getOwner();

		playerSelected.addScore(pieceEnemy.getScore());
		playerSelected.increaseMove();
		this.getBoard().setPiece(newPos, pieceSelected);
	}
}
