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
		command = new PieceCapturedCommand();
		
		eventMgr.addListener("PieceCapturedEvent", command);
	}
	
	@Test
	public void capture_enemy_piece() {
		// arrange
		Player player1 = game.getPlayer(1);
		Player player2 = game.getPlayer(2);
		
		PieceCapturedEvent event = new PieceCapturedEvent(0,30);

		// act
		eventMgr.fireEvent(event);

		
		// assert
		assertEquals("Player 1 get 5 score",
				5, player1.getScore());
		
		int tempcount = 0;
		for(Piece p : board.getPieces()){
			if(p != null && p.getOwner() == player2){
				tempcount++;
			}
		}
		
		assertEquals("Player 2 piece is reduced by 1",
				5, tempcount);
//		assertTrue(board.getPiece(30).getOwner().equals(player1));
	}

}
