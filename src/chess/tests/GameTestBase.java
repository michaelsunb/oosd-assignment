<<<<<<< HEAD
package chess.tests;

import chess.core.ChessEventDispatcher;
import chess.core.Game;
import chess.core.IBoard;

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
=======
package chess.tests;

import chess.core.Game;
import chess.core.IBoard;
import chess.prototype.observer.ChessEventDispatcher;

public abstract class GameTestBase {
	protected ChessEventDispatcher eventMgr;
	
	protected IBoard getBoard() {
		return Game.getInstance().getBoardInstance();
	}
	
	protected Game getGame() {
		return Game.getInstance();
	}
	
	protected ChessEventDispatcher eventMgr() {
		return ChessEventDispatcher.getInstance();
	}
	
	protected void eventMagr() {
		eventMgr = eventMgr();
	}
	
}
>>>>>>> origin/stabilize-part-2
