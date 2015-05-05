package chess.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import chess.prototype.composite.Rook;
import chess.prototype.events.PieceMovedEvent;
import chess.prototype.events.listener.PieceMovedEventListener;

public class PieceMoveTest extends GameTestBase {
	private PieceMovedEventListener command;
	
	@Before
	public void setUp() throws Exception {
		command = new PieceMovedEventListener();
		
		eventMgr.addListener("PieceMovedEvent", command);
	}
	
	@Test
	public void capture_enemy_piece() {
		// arrange
		PieceMovedEvent event = new PieceMovedEvent(0, 6);
		
		// act
		eventMgr.fireEvent(event);;
		
		// assert
		assertEquals("Rook moved to empty position",
				(new Rook()).getClass(), board.getPiece(6).getClass());
		assertEquals("Nothing should be in previous position",
				null, board.getPiece(0));
	}

}
