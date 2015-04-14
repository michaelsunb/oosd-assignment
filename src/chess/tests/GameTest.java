package chess.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import chess.core.Game;

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
}

