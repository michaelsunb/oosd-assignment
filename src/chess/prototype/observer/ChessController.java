package chess.prototype.observer;

import chess.core.Board;
import chess.prototype.composite.Rook;
import chess.view.PlayerDisplay;

public class ChessController {

	public static void main(String[] args) {
		Board chessData = new Board(6,6);

		new CollisionDisplay(chessData);
		LegalMoveDispaly w = new LegalMoveDisplay(chessData);
		new PlayerDisplay(chessData);

		chessData.state(plyer,new Rook());
	}
}
