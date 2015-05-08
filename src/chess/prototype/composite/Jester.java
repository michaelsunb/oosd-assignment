package chess.prototype.composite;

import chess.core.Piece;
import chess.prototype.builder.PathBuilder;

public class Jester extends Piece {

	public Jester() {
		this.score = 5;
		/*
		 * Knight Unicode
		 */
		this.symbol = 'J';
	}

	@Override
	public int[] getMovablePositions(int pos) {
		PathBuilder buildPositions = null;
		try {
			buildPositions = new PathBuilder.Builder(pos)
				.south()
				.north()
				.eastTwoNorthOne()
				.eastTwoSouthOne()
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
