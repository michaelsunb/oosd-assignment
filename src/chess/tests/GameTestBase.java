package chess.tests;

import chess.core.Game;
import chess.core.IBoard;
import chess.prototype.observer.ChessEventDispatcher;

public abstract class GameTestBase {
	protected Game game = Game.getInstance();
	protected IBoard board = Game.getInstance().getBoardInstance();
	protected ChessEventDispatcher eventMgr = ChessEventDispatcher.getInstance();

}
