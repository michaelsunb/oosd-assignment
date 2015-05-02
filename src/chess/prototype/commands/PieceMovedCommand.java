package chess.prototype.commands;

import chess.core.Board;
import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceCapturedEvent;
import chess.mvc.models.PieceJoinEvent;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.observer.ChessEvent;

public class PieceMovedCommand extends CommandBase {
	private PieceMovedEvent currentEvent;

	private int newPosition;
	private int oldPosition;
	private Piece selectedPiece;
	private Player selectedOwner;
	private Player targetOwner;

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceMovedEvent)) return;
		
		this.currentEvent = (PieceMovedEvent) event;
		newPosition = this.currentEvent.getNewPosition();
		oldPosition = this.currentEvent.getPreviousPosition();

		if(!isSelectedPieceValid()) return;

		if(!selectedPiece.canMoveTo(oldPosition, newPosition)) return;

		if(landingOnFriend()) {
			PieceJoinEvent join = new PieceJoinEvent(oldPosition, newPosition);
			this.eventMgr().fireEvent(join);
			return;
		}
		
		if(landingOnEnemy()) {
			PieceCapturedEvent capture = new PieceCapturedEvent(oldPosition, newPosition);
			this.eventMgr().fireEvent(capture);
			return;
		}
		
		// destination is empty so just occupy
		Piece piece = this.getBoard().getPiece(oldPosition);
		this.getBoard().getPieces()[newPosition] = piece;
		this.getBoard().getPieces()[oldPosition] = null;
		piece.getOwner().increaseMove();
	}

	private boolean landingOnFriend() {
		selectedOwner = selectedPiece.getOwner();
		targetOwner = !this.getBoard().isSqureEmpty(newPosition) 
				? this.getBoard().getPiece(newPosition).getOwner() : null;

		if(selectedOwner == targetOwner) {
			return true;
		}

		return false;
	}

	private boolean landingOnEnemy() {

		if(!this.getBoard().isSqureEmpty(newPosition) &&
				(targetOwner == null || selectedOwner != targetOwner)) {
			return true;
		}

		return false;
	}

	private boolean isSelectedPieceValid() {
		selectedPiece = this.getBoard().getPiece(oldPosition);

		if (selectedPiece == null || // selected piece is an empty square
				(selectedPiece != null && // selected piece has no owner
						selectedPiece.getOwner() == null))
			return false;
		
		return true;
	}
}
