package chess.prototype.composite;

import chess.core.Piece;

public class Rook extends Piece {

	public Rook() {
		this.score = 5;
	}
	
	@Override
	public int[] getMovablePositions() {
		return new int[]{ 1, 2, 4 };
	}

}
