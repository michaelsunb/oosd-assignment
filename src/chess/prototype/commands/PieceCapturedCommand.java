package chess.prototype.commands;

import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceCapturedEvent;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.IObserver;

public class PieceCapturedCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceCapturedEvent)) return;
		
		PieceCaptured((PieceCapturedEvent) event);
	}

	public void PieceCaptured(PieceCapturedEvent event) {
		Piece piece = event.getCapturedPiece();
		int position = event.getCapturedPosition();
		Player player = this.game.getCurrentPlayer();

		player.addScore(this.board.getPiece(position).getScore());

		board.setPiece(position, piece);
	}
}
