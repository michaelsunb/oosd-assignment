package chess.prototype.events.listener;

<<<<<<< HEAD:src/chess/prototype/events/listener/PieceMovedEventListener.java
import chess.core.Board;
import chess.core.Piece;
import chess.core.Player;
import chess.prototype.events.ChessEvent;
import chess.prototype.events.PieceCapturedEvent;
import chess.prototype.events.PieceJoinEvent;
import chess.prototype.events.PieceMovedEvent;

public class PieceMovedEventListener extends EventListenerBase {
=======
import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.template.MovedDecisionTemplate;

public class PieceMovedCommand extends MovedDecisionTemplate {
>>>>>>> origin/stabilize-part-2:src/chess/prototype/commands/PieceMovedCommand.java
	private PieceMovedEvent currentEvent;

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceMovedEvent)) return;
		
		this.currentEvent = (PieceMovedEvent) event;
		newPosition = this.currentEvent.getNewPosition();
		oldPosition = this.currentEvent.getPreviousPosition();
		if (oldPosition == -1) return;
		if (!isSelectedPieceNotEmptySqureBarrierOrEnemyPiece())
			return;
		if(!selectedPiece.canMoveTo(oldPosition, newPosition))
			return;
		
		this.moveDecider();
	}

	public void fireMoveCommand() {
		// destination is empty so just occupy
		this.getBoard().getPieces()[newPosition] = selectedPiece;
		this.getBoard().getPieces()[oldPosition] = null;
		
		selectedPiece.getOwner().increaseMove();
		this.getGame().swapPlayer();
	}
}
