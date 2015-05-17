package chess.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import chess.prototype.builder.PathBuilder;

public class PathBuilderTest extends GameTestBase {

	@Before
	public void setUp() throws Exception {
		this.getGame().reset(10);
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
	public void test_rook_dupl_path() {
		PathBuilder buildPositions = null;
		try {
			buildPositions = new PathBuilder.Builder(0)
				.north()
				.north()
				.south()
				.south()
				.east()
				.east()
				.west()
				.west()
				.build();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int[] moveablePositions = buildPositions.getMovablePositions();
		int[] testPositions = {1,2,6,12,18};

		assertFalse("Should be no duplicates",
				((moveablePositions[0] == 1)) &&
				(moveablePositions[1] == 1));
	
		for(int i : testPositions) {
			assertTrue("Testing this position: " + i,
					canMoveTo(moveablePositions,i));
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
	public void test_knight_path() {
		PathBuilder buildPositions = null;
		try {
			buildPositions = new PathBuilder.Builder(9)
				.northTwoEastOne()
				.northTwoWestOne()
				.southTwoEastOne()
				.southTwoWestOne()
				.eastTwoNorthOne()
				.eastTwoSouthOne()
				.westTwoNorthOne()
				.westTwoSouthOne()
				.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int[] testPositions = {22,20,17,13,5,1};
		for(int i : testPositions) {
			assertTrue("Testing this position: " + i,
					canMoveTo(buildPositions.getMovablePositions(),i));
		}
	}

	private boolean canMoveTo(int[] positions, int destination) {
		Arrays.sort(positions);
		return Arrays.binarySearch(positions, destination) >= -1;
	}
}
