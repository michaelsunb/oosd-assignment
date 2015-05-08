package chess.prototype.composite;

import chess.core.Piece;
import chess.prototype.builder.PathBuilder;

public class Knight extends Piece {

	public Knight() {
		this.score = 5;
		/*
		 * Knight Unicode
		 */
		this.symbol = '\u2658';
	}

	@Override
	public int[] getMovablePositions(int currPos) {
		PathBuilder buildPositions = null;
		try {
			buildPositions = new PathBuilder.Builder(currPos)
				.eastTwoNorthOne()
				.eastTwoSouthOne()
				.northTwoEastOne()
				.northTwoWestOne()
				.southTwoEastOne()
				.southTwoWestOne()
				.westTwoNorthOne()
				.westTwoSouthOne()
				.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buildPositions.getMovablePositions();
	}
}
