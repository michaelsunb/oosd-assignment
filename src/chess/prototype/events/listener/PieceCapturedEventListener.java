package chess.prototype.events.listener;

import chess.core.Piece;
import chess.core.Player;
import chess.prototype.events.ChessEvent;
import chess.prototype.events.PieceCapturedEvent;

public class PieceCapturedEventListener extends EventListenerBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceCapturedEvent)) return;
		
		update((PieceCapturedEvent) event);
	}

	public void update(PieceCapturedEvent event) {
		int newPos = event.getNewPosition();
		int oldPos = event.getPreviousPosition();

		Piece pieceSelected = board.getPiece(oldPos);
		Piece pieceEnemy = board.getPiece(newPos);

		if(pieceEnemy == null) return; // TODO

		Player playerSelected = pieceSelected.getOwner();

		playerSelected.addScore(pieceEnemy.getScore());
		board.setPiece(newPos, pieceSelected);
	}
}
