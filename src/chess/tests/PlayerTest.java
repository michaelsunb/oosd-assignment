/*
 * Author: Michaelsun Baluyos
 * Number: s3110401
 */
package chess.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import chess.core.Player;

public class PlayerTest {

	@Test
	public void test_player_score() {
		Player player = new Player();
		
		player.addScore(1);
		assertEquals("Captured Barrier", 1, player.getScore());
		
		player.addScore(5);
		assertEquals("Captured Barrier & a Piece", (1 + 5), player.getScore());

		player.addScore(10);
		assertEquals("Captured Barrier, a Piece & a CombinePiece", (1 + 5 + 10), player.getScore());
	}
	
	@Test
	public void invalid_score() {
		// arrange
		Player player = new Player();
		// act
		player.addScore(2);
		// assert
		assertNotSame("None of the Piece has score = 2, adding this value will not accepted",
				2, player.getScore());
	}
}
