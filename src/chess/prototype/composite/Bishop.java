package chess.prototype.composite;

import chess.core.Piece;

public class Bishop extends Piece {

	public Bishop() {
		this.score = 5;
	}
	
	@Override
	public int[] getMovablePositions() {
		return new int[]{ 1, 2 };
	}

}
