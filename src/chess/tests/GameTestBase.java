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
