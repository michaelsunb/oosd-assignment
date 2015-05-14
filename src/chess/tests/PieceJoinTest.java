package chess.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.core.Piece;
import chess.mvc.models.PieceMovedEvent;
import chess.prototype.commands.PieceJoinCommand;
import chess.prototype.commands.PieceMovedCommand;

public class PieceJoinTest extends GameTestBase {
	@Before
	public void setUp() throws Exception {
		this.eventMgr().removeAll();
		this.eventMgr().addListener("PieceMovedEvent", new PieceMovedCommand());
		this.eventMgr().addListener("PieceJoinEvent", new PieceJoinCommand());
		
		this.getGame().reset(10);
	}

	@Test
	public void merge_friendly_piece() {
		// arrange
		Piece rook = this.getBoard().getPiece(0);
		Piece knight = this.getBoard().getPiece(1);

		PieceMovedEvent event = new PieceMovedEvent(0, 1);

		// act
		this.eventMgr().fireEvent(event);

		// assert
		assertTrue("combine piece should not equal rook's score",
				(rook.getScore() != this.getBoard().getPiece(1).getScore()));

		assertEquals("Combine piece should equal rook + knight score",
				rook.getScore() + knight.getScore(), 
				this.getBoard().getPiece(1).getScore());

		assertEquals("Should be nothing at position zero",
				null, this.getBoard().getPiece(0));
		
		assertEquals("Player pieces reduced by 1", 
				5, this.getGame().getPlayerPieces(1).size());
		
		assertEquals("Moved increased by 1", 
				1, this.getGame().getPlayer(1).getNumberOfMove());
	}

}