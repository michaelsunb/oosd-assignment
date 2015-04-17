package chess.prototype.composite;

import chess.core.Piece;

public class Barrier extends Piece {

	public Barrier() {
		this.score = 1;
		/*
		 * Star Unicode Character
		 */
		this.symbol = '\u2605';
	}

	@Override
	public int[] getMovablePositions(int currPos) {
		return new int[] {};
	}

}
