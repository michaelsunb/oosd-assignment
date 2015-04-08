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

		int boardSize = height * width;

		if(currPos > 0 && currPos < boardSize) {
			// For all y
			int checkSame = -1;
			for(int i=0;i<=boardSize;i++) {
				int x = (currPos % width);
				int y = (i / height);
				int movable = x + (y * height);
				if(checkSame != movable && movable < boardSize) {
					positions.add(movable);
					checkSame = movable;
				}
			}
			// For all x
			for(int i=0;i<width;i++) {
				int y = (currPos / width);
				int movable = i + y + (y * (width-1));
				if(movable < boardSize) {
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
