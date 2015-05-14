package chess.prototype.commands;

import chess.core.Game;
import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceCapturedEvent;
import chess.mvc.models.PieceJoinEvent;

public abstract class MovedDecision extends CommandBase {

	protected int newPosition;
	protected int oldPosition;
	protected Piece selectedPiece;
	private Player selectedOwner;
	private Player targetOwner;

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

	protected boolean isSelectedPieceValid() {
		if(oldPosition == newPosition) return false;
		
		selectedPiece = this.getBoard().getPiece(oldPosition);
	
		if (selectedPiece == null || // selected piece is an empty square
				(selectedPiece != null && // selected piece has no owner
						selectedPiece.getOwner() == null))
			return false;
		
		return true;
	}

	public boolean move() {
		if(landingOnFriend()) {
			PieceJoinEvent join = new PieceJoinEvent(oldPosition, newPosition);
			this.eventMgr().fireEvent(join);
			return false;
		}
		
		if(landingOnEnemy()) {
			PieceCapturedEvent capture = new PieceCapturedEvent(oldPosition, newPosition);
			this.eventMgr().fireEvent(capture);
			return false;
		}

		return true;
	}

}