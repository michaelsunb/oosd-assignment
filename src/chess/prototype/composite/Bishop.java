package chess.prototype.composite;

import java.util.ArrayList;

import chess.core.Piece;

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
		return new int[]{ 1, 2 };
	}

}
