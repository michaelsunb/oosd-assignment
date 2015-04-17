package chess.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.core.Player;

public class PlayerTest {

	@Test
	public void test_player_score() {
		Player player = new Player();
		
		player.addScore(1);
		assertEquals("Number of move each player can take", 1, player.getScore());
		
		player.addScore(5);
		assertEquals("Number of move each player can take", (1 + 5), player.getScore());
		
		player.addScore(2);
		assertNotSame("Number of move each player can take", (1 + 5 + 2), player.getScore());
		
		player.addScore(10);
		assertEquals("Number of move each player can take", (1 + 5 + 10), player.getScore());
	}
}
