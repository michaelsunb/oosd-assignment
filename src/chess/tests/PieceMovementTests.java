package chess.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import chess.core.Game;
import chess.piece.commands.ChessPieceMoveCommand;
import chess.prototype.composite.Rook;

public class PieceMovementTests {

	@Test
	public void test() {
		
		Game.getInstance().reset(100);
		int total = Game.getInstance().getBoardInstance().getPieces().length;
//		IBoard board = new Board();
//		board.init();
//		Piece rook = new Rook();
		assertEquals(36, total);
		
		assertEquals(Rook.class, Game.getInstance().getBoardInstance().getPiece(0).getClass());
		
//		ChessPieceMoveCommand moveRookTest = new ChessPieceMoveCommand(0, 6);
//		moveRookTest.movePiece();
//		assertEquals(null, Game.getInstance().getBoardInstance().getPiece(0));
//		assertEquals(Rook.class, Game.getInstance().getBoardInstance().getPiece(6).getClass());
//		System.out.println("test");
	}

}
