package chess.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.core.Piece;
import chess.core.Player;
import chess.mvc.models.PieceCapturedEvent;
import chess.prototype.commands.*;

public class PieceCapturedTest extends GameTestBase {
	private PieceCapturedCommand command;
	
	@Before
	public void setUp() throws Exception {
		//
		game.reset(10);

		command = new PieceCapturedCommand();
		
		eventMgr.addListener("PieceCapturedEvent", command);
	}
	
	@Test
	public void capture_enemy_piece() {
		// arrange
		Player player1 = game.getPlayer(1);
		Piece capturedPiece = board.getPiece(30);
		
		PieceCapturedEvent event = new PieceCapturedEvent(player1, capturedPiece, 30);
		
		// act
		eventMgr.fireEvent(event);
		
		// assert
		assertEquals("Player 1 get 5 score",
				5, player1.getScore());
		assertEquals("Player 2 piece is reduced by 1",
				5, game.getPlayer(2).getPieces().size());
		assertTrue(board.getPiece(30).getOwner().equals(player1));
	}

}
