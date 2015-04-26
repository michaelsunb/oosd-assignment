package chess.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import chess.core.Piece;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.commands.PieceJoinCommand;

public class PieceJoinTest extends GameTestBase {
	private PieceJoinCommand command;
	
	@Before
	public void setUp() throws Exception {
		command = new PieceJoinCommand();
		
		eventMgr.addListener("PieceMovedEvent", command);
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
		assertNotEquals("combine piece should not equal rook's score",
				rook.getScore(), board.getPiece(1).getScore());

		assertEquals("Combine piece should equal rook + knight score",
				rook.getScore() + knight.getScore(),
				board.getPiece(1).getScore());

		assertEquals("Should be nothing at position zero",
				null,
				board.getPiece(0));
	}

}