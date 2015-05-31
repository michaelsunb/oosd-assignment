/*
 * Author: Sokun, CHORN
 * Number: S3455783
 */
package chess.tests;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import org.junit.*;
import chess.core.*;
import chess.prototype.composite.*;

public class GameTest {
	@Test
	public void game_instance_singleton() {
		Game game1 = Game.getInstance();
		Game game2 = Game.getInstance();

		assertEquals("Game object implements singleton pattern", game1, game2);
	}

	@Test
	public void game_reset() {
		Game game = Game.getInstance();
		game.reset(10);

		assertEquals("Number of move each player can take", 10, game.getMaxMoves());
		assertNotNull("Game always have valid player turn", game.getCurrentPlayer());
		assertTrue(game.getBoardInstance().getPiece(30) != null);
	}

	@Test
	public void game_get_players() {
		// arrange
		Game game = Game.getInstance();
		game.reset(10);

		// act
		Player p1 = game.getPlayer(1);
		Player p2 = game.getPlayer(2);

		// assert
		assertNotSame("There are two players in a game", p1, p2);
	}

	@Test
	public void game_swapTurn() {
		// arrange
		Game game = Game.getInstance();
		game.reset(10);
		game.getPlayer(1).setTurn(true);

		// act
		game.swapPlayer();

		// assert
		assertFalse("Player 1 lost turn to player 2", game.getPlayer(1).isTurn());
		assertTrue("Player 2 get turn", game.getPlayer(2).isTurn());

	}
	
	@Test
	public void get_player_pieces() {
		// arrange
		Game game = Game.getInstance();
		game.reset(10);

		// act
		List<Piece> pieces = game.getPlayerPieces(1);
		
		// assert
		assertEquals(6, pieces.size());
	}
	
	@Test
	public void save_game_state() {
		// arrange
		Game game = Game.getInstance();
		game.reset(10);
				
		// act
		game.save();
		
		// assert
		File file = new File("game.state");
		assertTrue(file.exists());
	}
	
	@Test
	public void restore_game_state() {
		// arrange
		Game game = Game.getInstance();
		game.reset(10);

		Player player2 = game.getPlayer(2);
		player2.increaseMove();

		Rook rook = new Rook();
		rook.setOwner(player2);

		game.getBoardInstance().setPiece(30, null);
		game.getBoardInstance().setPiece(7, rook);
		
		game.save();
		game.reset(11);
		
		// assert
		assertTrue(Game.getInstance().getBoardInstance().getPiece(7) == null);
		
		// act
		game.restore();
		
		// assert
		assertEquals("Max move restored", 10, Game.getInstance().getMaxMoves());

		assertEquals("Player 2 rook is not at position 30", null, Game.getInstance().getBoardInstance().getPiece(30));
		assertEquals("Player 2 rook is at position 7", rook.getClass(),
				Game.getInstance().getBoardInstance().getPiece(7).getClass());
	}
}
