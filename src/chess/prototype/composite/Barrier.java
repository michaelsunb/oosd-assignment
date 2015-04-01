package chess.prototype.composite;

import chess.core.Piece;

public class Barrier extends Piece {

	public Barrier()
	{
		this.score = 1;
	}
	
	@Override
	public int[] getMovablePositions() {
		return new int[]{};
	}

}
