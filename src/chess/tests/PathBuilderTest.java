package chess.tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import chess.prototype.builder.PathBuilder;
import chess.prototype.commands.PieceJoinCommand;
import chess.prototype.commands.PieceMovedCommand;

public class PathBuilderTest extends GameTestBase {
	private int boardSize;
	
	@Before
	public void setUp() throws Exception {
		this.getGame().reset(10);
		boardSize = 
				this.getBoard().getHeight() * this.getBoard().getWidth();
	}

	@Test
	public void test_rook_path() {
		PathBuilder buildPositions = null;
		try {
			buildPositions = new PathBuilder.Builder(0)
				.north()
				.south()
				.east()
				.west()
				.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int[] testPositions = {6,12,1,2,3,4,5};

		for(int i : testPositions) {
			assertTrue("Testing this position: " + i,
					canMoveTo(buildPositions.getMovablePositions(),i));
		}

		int[] testFailPositions = {7,8,9,10,11};
		for(int i : testFailPositions) {
			assertEquals("Should not equal these positions: " + i,
					false,canMoveTo(buildPositions.getMovablePositions(),i));
		}
	}

	@Test
	public void test_bishop_path() {
		PathBuilder buildPositions = null;
		try {
			buildPositions = new PathBuilder.Builder(9)
				.northEast()
				.northWest()
				.southEast()
				.southWest()
				.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int[] testPositions = {3,6,7,8,10,11};

		for(int i : testPositions) {
			assertTrue("Testing this position: " + i,
					canMoveTo(buildPositions.getMovablePositions(),i));
		}

		int[] testFailPositions = {7,8,9,10,11};
		for(int i : testFailPositions) {
			assertEquals("Should not equal these positions: " + i,
					false,canMoveTo(buildPositions.getMovablePositions(),i));
		}
	}

	private boolean canMoveTo(int[] positions, int destination) {
		return Arrays.binarySearch(positions, destination) >= -1;
	}
}
