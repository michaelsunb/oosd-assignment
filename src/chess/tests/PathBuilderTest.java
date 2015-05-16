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

		int[] testPositions = {6,12,1};//,2,3,4,5}; positions is blocked
									   // because barriers is in the way

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

		int[] testPositions = {2,4,16,14};
		
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
	public void quick_test_canMoveTo_method() {
		int[] testPositions = {6,4,16,14};
		int[] samePositions = {6,4,16,14};
		int[] sortPositions = {4,6,14,16};
		int[] failPositions = {7,3,15,12};
		int[] failSortPositions = {3,7,12,15};
		int[] failPassPositions = {3,4,16,15};
		int[] failLongPositions = {3,7,19,15,9,10,11};
		
		for(int i : testPositions) {
			assertTrue("Pass position: " + i,
					canMoveTo(samePositions,i));
		}
		for(int i : testPositions) {
			assertTrue("Pass position: " + i,
					canMoveTo(sortPositions,i));
		}
		for(int i : testPositions) {
			assertFalse("Fail position: " + i,
					canMoveTo(failPositions,i));
		}
		for(int i : testPositions) {
			assertFalse("Fail position: " + i,
					canMoveTo(failSortPositions,i));
		}
		for(int i : testPositions) {
			assertFalse("Fail position: " + i,
					canMoveTo(failLongPositions,i));
		}
		for(int i : testPositions) {
			if(i == 4 || i == 16) {
				assertTrue("Pass at position 4 and 16",
						canMoveTo(failPassPositions,i));
			} else {
				assertFalse("Fail position: " + i,
						canMoveTo(failPassPositions,i));
			}
		}
	}

	private boolean canMoveTo(int[] positions, int destination) {
		Arrays.sort(positions);
		return Arrays.binarySearch(positions, destination) >= -1;
	}
}
