package chess.prototype.template;

import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceCapturedEvent;
import chess.mvc.models.PieceJoinEvent;
import chess.prototype.commands.CommandBase;
import chess.prototype.observer.ChessEvent;

public abstract class MovedDecisionTemplate extends CommandBase {
	
	protected int newPosition;
	protected Piece selectedPiece;
	private Player selectedOwner;
	private Player targetOwner;
	
	private ChessEvent nextEvent = null;

	private void landingOnFriend() {
		selectedOwner = selectedPiece.getOwner();
		targetOwner = !this.getBoard().isSqureEmpty(newPosition) 
				? this.getBoard().getPiece(newPosition).getOwner() : null;
	
		if(selectedOwner == targetOwner) return;

		this.nextEvent = new PieceJoinEvent(oldPosition, newPosition);
	}

	private void landingOnEnemy() {
	
		if(!this.getBoard().isSqureEmpty(newPosition) &&
				(targetOwner == null || selectedOwner != targetOwner)) {
			return;
		}
	
		this.nextEvent = new PieceCapturedEvent(oldPosition, newPosition);
	}

	private boolean isSelectedPieceNotEmptySqureBarrierOrEnemyPiece() {
		if(oldPosition == newPosition) return false;
		
		selectedPiece = this.getBoard().getPiece(oldPosition);
	
		if (selectedPiece == null || // selected piece is an empty square
				(selectedPiece != null && // selected piece has no owner
						selectedPiece.getOwner() == null))
			return false;
		return true;
	}

	public void moveDecider() {
		if(!this.isSelectedPieceNotEmptySqureBarrierOrEnemyPiece()) return;
		this.landingOnFriend();
		this.landingOnEnemy();
		
		if(this.nextEvent == null) {
			// template method
			this.fireMoveCommand();
		} else {
			this.fireNextEvent();
		}
	}
	
	public abstract void fireMoveCommand();
	
	private void fireNextEvent() {
		this.eventMgr().fireEvent(this.nextEvent);
	}

}