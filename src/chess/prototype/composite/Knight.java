package chess.prototype.composite;

import chess.core.Piece;

public class Knight extends Piece {
	public Knight()
	{
		this.score = 5;
	}
	
	@Override
	public int[] getMovablePositions() {
		return new int[]{ 1, 2 };
	}

}
