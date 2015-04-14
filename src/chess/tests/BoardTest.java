package chess.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.core.Board;
import chess.core.IBoard;

public class BoardTest {

	@Test
	public void board_initialization() {
		IBoard board = new Board();
		board.init();
		
		assertEquals(36, board.getPieces().length);
	}

}
