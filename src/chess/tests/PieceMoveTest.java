<<<<<<< HEAD
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
=======
package chess.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import chess.mvc.models.PieceMovedEvent;
import chess.prototype.commands.PieceMovedCommand;
import chess.prototype.composite.CombinePiece;
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

	@Test
	public void move_combined_piece() {
		// arrange
		CombinePiece combined = new CombinePiece();
		combined.add(this.getBoard().getPiece(0));
		combined.add(this.getBoard().getPiece(1));
		combined.setOwner(this.getBoard().getPiece(0).getOwner());
		
		this.getBoard().setPiece(0, combined);
		this.getBoard().setPiece(1, null);
		
		PieceMovedEvent event = new PieceMovedEvent(0, 6);

		// act
		this.eventMgr().fireEvent(event);

		// assert
		assertEquals("Nothing should be in previous position", null, this
				.getBoard().getPiece(0));
		assertEquals("Player 1 move increased by 1", 1, this.getGame()
				.getPlayer(1).getNumberOfMove());
		assertEquals("Combined piece moved", combined, this.getBoard().getPiece(6));
	}
}
>>>>>>> origin/stabilize-part-2
