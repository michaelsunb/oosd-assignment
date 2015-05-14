package chess.prototype.composite;

import chess.core.Piece;
import chess.prototype.builder.PathBuilder;

public class Musician extends Piece {

	public Musician() {
		this.score = 5;
		/*
		 * Knight Unicode
		 */
		this.symbol = 'M';
	}

	@Override
	public int[] getMovablePositions(int currPos) {
		PathBuilder buildPositions = null;
		try {
			buildPositions = new PathBuilder.Builder(currPos)
				.east()
				.west()
				.northTwoEastOne()
				.northTwoWestOne()
				.southTwoEastOne()
				.southTwoWestOne()
				.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buildPositions.getMovablePositions();
	}

}
