package chess.prototype.commands;

import chess.core.Game;
import chess.core.IBoard;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;

public abstract class CommandBase implements IObserver {
	private final Game game;
	private final IBoard board;
	private final ChessEventDispatcher eventMgr;
	
	public CommandBase() {
		this.game = Game.getInstance();
		this.board = game.getBoardInstance();
		this.eventMgr = ChessEventDispatcher.getInstance();
	}
	
	public Game getGame() {
		return game;
	}
	
	public IBoard getBoard () {
		return board;
	}
	
	public ChessEventDispatcher eventMgr() {
		return eventMgr; 
	}
	
	@Override
	public abstract void update(ChessEvent event);
}
