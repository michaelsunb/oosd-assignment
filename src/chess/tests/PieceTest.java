package chess.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.core.Piece;
import chess.prototype.composite.Barrier;
import chess.prototype.composite.CombinePiece;
import chess.prototype.composite.Knight;
import chess.prototype.composite.Rook;

public class PieceTest {

	@Test
	public void rook_can_move() {
		Piece p = new Rook();
		
		assertEquals(5, p.getScore());
		
		assertTrue("Can move", p.getMovablePositions().length > 0);
	}

	@Test
	public void barrier_cannot_move()
	{
		Piece p = new Barrier();
		
		assertEquals(1, p.getScore());
		
		assertTrue("Can't move", p.getMovablePositions().length == 0);
	}
	
	@Test
	public void combine_piece_move()
	{
		Piece p = new CombinePiece();
		((CombinePiece)p).add(new Rook());
		((CombinePiece)p).add(new Knight());
		
		assertEquals(10, p.getScore());
		assertTrue("Combine move", p.getMovablePositions().length == 3);
	}
	
	@Test
	public void split_piece_move()
	{
		Piece p = new CombinePiece();
		Piece rook = new Rook();
		Piece knight = new Knight();
		
		((CombinePiece)p).add(rook);
		((CombinePiece)p).add(knight);
		
		((CombinePiece)p).remove(rook);
		
		assertEquals(5, p.getScore());
		
		assertTrue("Combine move", p.getMovablePositions().length == 2);
	}	
}
