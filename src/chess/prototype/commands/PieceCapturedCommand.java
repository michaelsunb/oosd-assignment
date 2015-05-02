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

		Piece selectedPiece = this.getBoard().getPiece(oldPos);
		Piece enemyPiece = this.getBoard().getPiece(newPos);

		// Move to empty square is not the responsibility of this
		// object
		if(enemyPiece == null) return; 


		Player currentPlayer = selectedPiece.getOwner();

		currentPlayer.addScore(enemyPiece.getScore());
		currentPlayer.increaseMove();
		
		this.getBoard().setPiece(newPos, selectedPiece);
	}
}
