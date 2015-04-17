/*
 * Author: Sokun, CHORN
 * Student Number: S3455783
 */
package chess.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import chess.core.*;
import chess.prototype.composite.*;

public class PieceTest {

	@Test
	public void rook_can_move() {
		// arrange
		// act
		Piece p = new Rook();

		// assert
		assertEquals(5, p.getScore());
		assertTrue("Can move", p.getMovablePositions(1).length > 1);
	}

	@Test
	public void barrier_cannot_move() {
		// arrange
		// act
		Piece p = new Barrier();

		// assert
		assertEquals(1, p.getScore());
		assertTrue("Can't move", p.getMovablePositions(1).length == 0);
	}

	@Test
	public void combine_piece_move() {
		// arrange
		Piece p = new CombinePiece();
		Rook rook = new Rook();
		Knight knight = new Knight();

		// act
		((CombinePiece) p).add(rook);
		((CombinePiece) p).add(knight);

		// assert
		assertEquals("Combined piece value ", 10, p.getScore());
		int combinedMove = p.getMovablePositions(1).length;
		assertTrue("Combined move larger than individual move",
				combinedMove > rook.getMovablePositions(1).length
						&& combinedMove > knight.getMovablePositions(1).length);
	}

	@Test
	public void split_piece_move() {
		// arrange
		Piece p = new CombinePiece();
		Piece rook = new Rook();
		Piece knight = new Knight();

		// act
		((CombinePiece) p).add(rook);
		((CombinePiece) p).add(knight);

		((CombinePiece) p).remove(rook);

		// assert
		assertEquals(5, p.getScore());
		assertTrue("Split piece (move of the Knight)",
				p.getMovablePositions(1).length == knight.getMovablePositions(1).length);
	}

	@Test
	public void should_not_combine_the_same_piece_type_twice() {
		// arrange
		CombinePiece compositePiece = new CombinePiece();

		// act
		compositePiece.add(new Rook());
		boolean result = compositePiece.add(new Rook());

		// assert
		assertFalse("Rook already combine", result);
	}

	@Test
	public void should_combine_up_to_three_different_piece_type() {
		// arrange
		CombinePiece compositePiece = new CombinePiece();

		// act
		compositePiece.add(new Rook());
		compositePiece.add(new Bishop());
		boolean result = compositePiece.add(new Knight());

		// assert
		assertTrue("Can combine three different piece type", result);

		// act
		result = compositePiece.add(new Knight());
		assertFalse("Knight piece already combine", result);
	}

}
