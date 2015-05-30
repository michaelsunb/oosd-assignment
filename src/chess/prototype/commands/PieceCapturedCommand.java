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
		newPosition = event.getNewPosition();
		oldPosition = event.getPreviousPosition();

		Piece selectedPiece = this.getBoard().getPiece(oldPosition);
		Piece enemyPiece = this.getBoard().getPiece(newPosition);

		Player currentPlayer = selectedPiece.getOwner();
		currentPlayer.addScore(enemyPiece.getScore());
		
		this.getBoard().setPiece(newPosition, selectedPiece);
		this.getBoard().setPiece(oldPosition, null);
		
		currentPlayer.increaseMove();
		this.getGame().swapPlayer();
	}
}
