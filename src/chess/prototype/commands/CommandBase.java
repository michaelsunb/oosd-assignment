package chess.prototype.commands;

import chess.core.Game;
import chess.core.IBoard;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;

public abstract class CommandBase implements IObserver {
	protected int oldPosition;
	protected int newPosition;

	/**
	 * @pre.condition: Game object must be instantiated
	 * @post.condition: Instantiate child class with selected
	 * position set to old position 
	 */
	public CommandBase() {
		this.oldPosition = getGame().getSelPosition();
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
