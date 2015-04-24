package chess.tests;

import chess.core.Game;
import chess.core.IBoard;
import chess.prototype.observer.ChessEventDispatcher;

public abstract class GameTestBase {
	protected Game game;
	protected IBoard board;
	protected ChessEventDispatcher eventMgr;

	public GameTestBase() {
		game = Game.getInstance();
		game.reset(10);
		board = game.getBoardInstance();
		eventMgr = ChessEventDispatcher.getInstance();
	}
}
