package chess.prototype.events.listener;

import chess.core.ChessEventDispatcher;
import chess.core.Game;
import chess.core.IBoard;
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
	}
}
