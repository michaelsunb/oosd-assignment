package chess.prototype.commands;

import chess.core.Game;
import chess.core.IBoard;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;

public abstract class CommandBase implements IObserver {
	protected int oldPosition;
	protected int newPosition;

	public CommandBase() {
		this.oldPosition = getBoard().getPiecePosition(getGame().getSelectedPiece());
	}
	
	@Override
	public abstract void update(ChessEvent event);
	
	public Game getGame() {
		return Game.getInstance();
	}
	
	public IBoard getBoard () {
		return Game.getInstance().getBoardInstance();
	}
	
	public ChessEventDispatcher eventMgr() {
		return ChessEventDispatcher.getInstance(); 
	}
	
	public final int getNewPosition() {
		return newPosition;
	}

	public final int getOldPosition() {
		return oldPosition;
	}
}
