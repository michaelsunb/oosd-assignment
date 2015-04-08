package chess.prototype.composite;

import chess.core.Piece;

public class Knight extends Piece {
	public Knight()
	{
		this.score = 5;
		/*
		 * Knight Unicode
		 */
		this.symbol = '\u2658';
	}
	
	@Override
	public int[] getMovablePositions(int currPos) {
		return new int[]{ 1, 2 };
	}

}
