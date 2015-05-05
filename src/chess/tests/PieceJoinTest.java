package chess.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.core.Piece;
import chess.prototype.events.PieceMovedEvent;
import chess.prototype.events.listener.PieceJoinEventListener;

public class PieceJoinTest extends GameTestBase {
	private PieceJoinEventListener command;
	
	@Before
	public void setUp() throws Exception {
		command = new PieceJoinEventListener();

		eventMgr.addListener("PieceMovedEvent", command);
		//eventMgr.addListener("PieceJoinEvent", command);
	}
	
	@Test
	public void merge_friendly_piece() {
		// arrange
		Piece rook = board.getPiece(0);
		Piece knight = board.getPiece(1);
		
		PieceMovedEvent event = new PieceMovedEvent(0,1);

		// act
		eventMgr.fireEvent(event);

		// assert
		assertTrue("combine piece should not equal rook's score",
				(rook.getScore() != board.getPiece(1).getScore()));

		assertEquals("Combine piece should equal rook + knight score",
				rook.getScore() + knight.getScore(),
				board.getPiece(1).getScore());

		assertEquals("Should be nothing at position zero",
				null,
				board.getPiece(0));
	}

}