package chess.prototype.commands;

import chess.core.Game;
import chess.core.IBoard;
import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;

public abstract class CommandBase implements IObserver {
	
	public CommandBase() {
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
}
