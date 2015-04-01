package chess.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.core.Board;
import chess.core.IBoard;
import chess.prototype.composite.Barrier;
import chess.prototype.decorator.BarrierPieceDecorator;

public class BarrierDecoratorTest {

	@Test
	public void setup_barrier() {
		// arrange
		IBoard board = new Board();
		board.init();
		
		// 
		new BarrierPieceDecorator(board).init();
		
		// assert
		assertTrue(board.getPiece(17) instanceof Barrier);
	}

}
