package chess.prototype.template;

import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceCapturedEvent;
import chess.mvc.models.PieceJoinEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.commands.CommandBase;
import chess.prototype.momento.GameCaretaker;
import chess.prototype.momento.GameMemento;
import chess.prototype.observer.ChessEvent;

public abstract class MovedDecisionTemplate extends CommandBase {

	protected Piece selectedPiece;
	private Piece enemyPiece;
	private Player selectedOwner;
	
	private Player targetOwner = null;
	private ChessEvent nextEvent = null;

	private void landingOnFriend() {
		selectedOwner = selectedPiece.getOwner();
		targetOwner = !this.getBoard().isSqureEmpty(newPosition) 
				? this.getBoard().getPiece(newPosition).getOwner() : null;

		if(targetOwner == null) return;
		if(selectedOwner.isTurn() != targetOwner.isTurn()) return;

		this.nextEvent = new PieceJoinEvent(oldPosition, newPosition);
	}

	private void landingOnEnemy() {
		if(this.getBoard().isSqureEmpty(newPosition) ||
				selectedOwner.equals(targetOwner)) {
			return;
		}

		this.nextEvent = new PieceCapturedEvent(oldPosition, newPosition);
	}

	private boolean pieceTurn() {
		selectedOwner = selectedPiece.getOwner();
		if(selectedOwner.isTurn() == this.getGame().getCurrentPlayer().isTurn()) {
			return true;
		}
		return false;
	}

	protected boolean isSelectedPieceNotEmptySqureBarrierOrEnemyPiece() {
		if(oldPosition == newPosition) return false;
		
		selectedPiece = this.getBoard().getPiece(oldPosition);
		enemyPiece = this.getBoard().getPiece(newPosition);

		if (selectedPiece == null || // selected piece is an empty square
				(selectedPiece != null && // selected piece has no owner
						selectedPiece.getOwner() == null))
			return false;
		return true;
	}

	/**
	 * @pre.condition: Must be called by the child class to execute
	 * template method pattern.
	 * @post.condition: Fires next events according to methods above
	 */
	public void moveDecider() {
		if(!this.pieceTurn()) return;
		
		this.nextEvent = null;

		this.landingOnFriend();
		this.landingOnEnemy();

		this.setMomento();

		if(this.nextEvent == null) {
			// template method
			this.fireMoveCommand();
		} else {
			this.fireNextEvent();
		}
		this.fireUIEvent();
	}
	
	/**
	 * @pre.condition: Old position is a piece and new position is empty and
	 * must have a child class to execute this method
	 * @post.condition: Piece has moved to the new position and old position is now empty
	 */
	public abstract void fireMoveCommand();

	/**
	 * @pre.condition: A move/split/join/capture command has been executed.
	 * @post.condition: Momento caretaker has a new stack of previous moves.
	 */
	private void setMomento() {
		GameCaretaker caretaker = this.getGame().getCaretaker();
		Integer[] playerScores = new Integer[2];
		playerScores[0] = this.getGame().getPlayer(1).getScore();
		playerScores[1] = this.getGame().getPlayer(2).getScore();
		Integer[] numberOfMove = new Integer[2];
		numberOfMove[0] = this.getGame().getPlayer(1).getNumberOfMove();
		numberOfMove[1] = this.getGame().getPlayer(2).getNumberOfMove();
		Boolean[] playerTurns = new Boolean[2];
		playerTurns[0] = this.getGame().getPlayer(1).isTurn();
		playerTurns[1] = this.getGame().getPlayer(2).isTurn();
		
		GameMemento originator = new GameMemento(oldPosition, newPosition, selectedPiece, enemyPiece, playerScores, numberOfMove, playerTurns);
		caretaker.addMemento(originator);
	}
	
	private void fireNextEvent() {
		this.eventMgr().fireEvent(this.nextEvent);
	}

	
	private void fireUIEvent() {
		this.eventMgr().fireEvent(new UpdateUIEvent());
	}
}