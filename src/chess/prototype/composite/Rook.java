package chess.prototype.composite;

import java.util.ArrayList;

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
	public int[] getMovablePositions(int currPos) {
		ArrayList<Integer> positions = new ArrayList<Integer>();
		if(currPos > 0 && currPos < 36) {
			// For all y
			int checkSame = -1;
			for(int i=0;i<=36;i++) {
				int x = (currPos % 6);
				int y = (i / 5);
				int movable = x + (y * 6);
				if(checkSame != movable && movable < 36) {
					positions.add(movable);
					checkSame = movable;
				}
			}
			// For all x
			for(int i=0;i<=5;i++) {
				int y = (currPos / 6);
				int movable = i + y + (y * 5);
				if(movable < 36) {
					positions.add(movable);
				}
			}
		}
		
		int[] ret = new int[positions.size()];
		int i = 0;
		for(Integer pos: positions) {
			ret[i++] = pos;
		}
		
		return ret;
	}
}
