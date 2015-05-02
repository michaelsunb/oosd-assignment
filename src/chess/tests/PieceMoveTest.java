package chess.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import chess.mvc.models.PieceMovedEvent;
import chess.prototype.commands.PieceMovedCommand;
import chess.prototype.composite.Rook;

public class PieceMoveTest extends GameTestBase {
	@Before
	public void setUp() throws Exception {
		this.eventMgr().removeAll();
		this.eventMgr().addListener("PieceMovedEvent", new PieceMovedCommand());
		this.getGame().reset(10);
	}

	@Test
	public void piece_moved() {
		// arrange
		PieceMovedEvent event = new PieceMovedEvent(0, 6);

		// act
		this.eventMgr().fireEvent(event);

		// assert
		assertEquals("Rook moved to empty position", (new Rook()).getClass(),
				this.getBoard().getPiece(6).getClass());
		assertEquals("Nothing should be in previous position", null, this
				.getBoard().getPiece(0));
		assertEquals("Player 1 move increased by 1", 1, this.getGame()
				.getPlayer(1).getNumberOfMove());
	}

}
