package chess.prototype.composite;

import chess.core.Piece;
import chess.prototype.builder.PathBuilder;

public class Bishop extends Piece {
	public Bishop() {
		this.score = 5;
		/*
		 * Bishop unicode character value
		 */
		this.symbol = '\u2657';
	}

	@Override
	public int[] getMovablePositions(int currPos) {
		PathBuilder buildPositions = null;
		try {
			buildPositions = new PathBuilder.Builder(currPos)
				.northEast()
				.northWest()
				.southEast()
				.southWest()
				.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buildPositions.getMovablePositions();
	}

}
