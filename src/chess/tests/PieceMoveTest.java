package chess.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import chess.mvc.models.PieceMovedEvent;
import chess.prototype.commands.PieceMovedCommand;
import chess.prototype.composite.Rook;

public class PieceMoveTest extends GameTestBase {
	private PieceMovedCommand command;
	
	@Before
	public void setUp() throws Exception {
		command = new PieceMovedCommand();
		
		eventMgr.addListener("PieceMovedEvent", command);
	}
	
	@Test
	public void capture_enemy_piece() {
		// arrange
		PieceMovedEvent event = new PieceMovedEvent(0, 6);
		
		// act
		command.movePiece(event);;
		
		// assert
		assertEquals("Rook moved to empty position",
				(new Rook()).getClass(), board.getPiece(6).getClass());
		assertEquals("Rook did not move",
				null, board.getPiece(0));
	}

}
