package chess.prototype.events.listener;

import chess.core.ChessEventDispatcher;
import chess.core.Game;
import chess.core.IBoard;
<<<<<<< HEAD:src/chess/prototype/events/listener/EventListenerBase.java
import chess.core.Piece;
import chess.core.Player;
import chess.prototype.events.ChessEvent;
import chess.prototype.events.PieceMovedEvent;

public abstract class EventListenerBase implements EventListener {
	protected Game game;
	protected IBoard board;
	protected ChessEventDispatcher eventMgr;
	
	public EventListenerBase() {
		game = Game.getInstance();
		board = game.getBoardInstance();
		eventMgr = ChessEventDispatcher.getInstance();
=======
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;

public abstract class CommandBase implements IObserver {
	protected int oldPosition;
	protected int newPosition;

	public CommandBase() {
		this.oldPosition = getGame().getSelPosition();
>>>>>>> origin/stabilize-part-2:src/chess/prototype/commands/CommandBase.java
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
