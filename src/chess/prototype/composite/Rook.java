package chess.prototype.composite;

import chess.core.Piece;
import chess.prototype.builder.PathBuilder;

public class Rook extends Piece {
	public Rook() {
		this.score = 5;
		/*
		 * Rook Unicode
		 */
		this.symbol = '\u265C';
	}

	@Override
	public int[] getMovablePositions(int currPos) {
		PathBuilder buildPositions = null;
		try {
			buildPositions = new PathBuilder.Builder(currPos)
				.north()
				.south()
				.east()
				.west()
				.build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buildPositions.getMovablePositions();
	}
}
