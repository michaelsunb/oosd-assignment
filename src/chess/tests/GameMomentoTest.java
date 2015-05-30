package chess.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import chess.core.Game;
import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.UndoEvent;
import chess.prototype.commands.PieceCapturedCommand;
import chess.prototype.commands.PieceJoinCommand;
import chess.prototype.commands.PieceMovedCommand;
import chess.prototype.composite.Barrier;
import chess.prototype.composite.Rook;
import chess.prototype.momento.UndoCommand;

public class GameMomentoTest extends GameTestBase {
	@Before
	public void setUp() throws Exception {
		this.eventMgr().removeAll();

		this.eventMgr().addListener("PieceMovedEvent", new PieceMovedCommand());
		this.eventMgr().addListener("PieceJoinEvent", new PieceJoinCommand());
		this.eventMgr().addListener("PieceCapturedEvent", new PieceCapturedCommand());
		this.eventMgr().addListener("UndoEvent", new UndoCommand());
		this.getGame().reset(10);
		
		PieceMovedEvent event = new PieceMovedEvent(0, 6);
		this.eventMgr().fireEvent(event);
	}

	@Test
	public void testUndoOnce() {
		this.eventMgr().fireEvent(new UndoEvent(1));

		// assert
		assertEquals("Rook moved back to empty position", (new Rook()).getClass(),
				this.getBoard().getPiece(0).getClass());
		assertEquals("Nothing should be in previous position", null, this
				.getBoard().getPiece(6));
		assertEquals("Player 1 move back by 1", 0, this.getGame()
				.getPlayer(1).getNumberOfMove());
	}

	@Test
	public void testUndoTwice() {
		this.eventMgr().fireEvent(new PieceMovedEvent(30, 18)); // move twice
		this.eventMgr().fireEvent(new PieceMovedEvent(6, 12)); // move three
		
		this.eventMgr().fireEvent(new UndoEvent(2));

		// assert
		assertEquals("Rook moved back to empty position", (new Rook()).getClass(),
				this.getBoard().getPiece(6).getClass());
		assertEquals("Nothing should be in previous position", (new Barrier()).getClass(), this
				.getBoard().getPiece(12).getClass());
		assertEquals("Player 1 move back by 1", 1, this.getGame()
				.getPlayer(1).getNumberOfMove());
	}

	@Test
	public void testUndoThree() {
		this.eventMgr().fireEvent(new PieceMovedEvent(30, 18)); // move twice
		this.eventMgr().fireEvent(new PieceMovedEvent(6, 12)); // move three
		this.eventMgr().fireEvent(new PieceMovedEvent(18, 19)); // move four
		this.eventMgr().fireEvent(new PieceMovedEvent(12, 18)); // move five
		
		
		assertEquals("Caretaker stack should be no more than 3", 3,
				Game.getInstance().getCaretaker().count());

		this.eventMgr().fireEvent(new UndoEvent(3));

		// assert
		assertEquals("Rook moved back to empty position", (new Rook()).getClass(),
				this.getBoard().getPiece(6).getClass());
		assertEquals("Nothing should be in previous position", (new Barrier()).getClass(), this
				.getBoard().getPiece(12).getClass());
		assertEquals("Player 1 move back by 1", 1, this.getGame()
				.getPlayer(1).getNumberOfMove());
	}
}
