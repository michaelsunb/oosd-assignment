/*
 * Author: Sokun, CHORN
 * Number: S3455783
 */
package chess.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.core.Board;
import chess.core.IBoard;
import chess.prototype.composite.Rook;

public class BoardTest {

	@Test
	public void board_initialization() {
		IBoard board = new Board();
		board.init();

		assertEquals(36, board.getPieces().length);
	}

	@Test
	public void get_piece_position() {
		IBoard board = new Board();
		board.init();
		Rook rook = new Rook();
		board.setPiece(5, rook);
		
		// act
		int pos = board.getPiecePosition(rook);
		
		// assert 
		assertEquals("Get pice position", 5, pos);
		
	}
}
