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
	protected Game game;
	protected IBoard board;
	protected ChessEventDispatcher eventMgr;
	
	public CommandBase() {
		game = Game.getInstance();
		board = game.getBoardInstance();
		eventMgr = ChessEventDispatcher.getInstance();
	}
	
	@Override
	public abstract void update(ChessEvent event);
}
