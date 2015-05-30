<<<<<<< HEAD:src/chess/prototype/events/listener/PieceCapturedEventListener.java
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
=======
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
>>>>>>> origin/stabilize-part-2:src/chess/prototype/commands/PieceCapturedCommand.java
