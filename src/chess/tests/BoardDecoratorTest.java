package chess.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.core.*;
import chess.prototype.composite.*;
import chess.prototype.decorator.*;

public class BoardDecoratorTest {

	@Test
	public void decorate_one_player_pieces() {
		// arrange
		IBoard board = new Board();
		board.init();
		Player p1 = new Player();
		
		// act
		new PlayerPieceDecorator(board, p1).init();
		
		// assert
		Piece p = board.getPiece(0);
		
		assertTrue(p != null);
		assertTrue(p.getOwner().equals(p1));
		
	}

	@Test
	public void decorate_two_players_pieces() {
		// arrange
		IBoard board = new Board();
		board.init();
		Player p1 = new Player();
		Player p2 = new Player();
		
		// act
		new PlayerPieceDecorator(board, p1).init();
		new PlayerPieceDecorator(board, p2).init();

		// assert
		Piece p = board.getPiece(0);
		
		assertTrue(p != null);
		assertTrue(p.getOwner().equals(p1));
		
		p = board.getPiece(30);
		
		assertTrue(p != null);
		assertTrue(p.getOwner().equals(p2));
		
	}
}
