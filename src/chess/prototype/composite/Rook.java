package chess.prototype.composite;

import chess.core.Piece;

public class Rook extends Piece {

	public Rook() {
		this.score = 5;
		/*
		 * Rook Unicode
		 */
		this.symbol = '\u265C';
	}
	
	@Override
	public int[] getMovablePositions() {
		return new int[]{ 1, 2, 4 };
	}

}
