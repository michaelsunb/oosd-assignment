package chess.prototype.commands;

import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceCapturedEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;

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

		Player currentPlayer = selectedPiece.getOwner();
		currentPlayer.addScore(enemyPiece.getScore());
		
		this.getBoard().setPiece(newPos, selectedPiece);
		this.getBoard().setPiece(oldPos, null);
		
		currentPlayer.increaseMove();
		this.getGame().swapPlayer();
		ChessEventDispatcher.getInstance().fireEvent(new UpdateUIEvent());
	}
}
