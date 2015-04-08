package chess.prototype.composite;

import java.util.ArrayList;

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
		ArrayList<Integer> positions = new ArrayList<Integer>();
		if(currPos > 0 && currPos < 36) {
			int x = (currPos % 6);
			int y = (currPos / 6);
			if((x - 2) >= 0) {
				if((y - 1) >= 0) {
					positions.add(getCurrentPosition((x - 2),(y - 1)));
				}
				if((y + 1) <= 36) {
					positions.add(getCurrentPosition((x - 2),(y + 1)));
				}
			}
			if((x + 2) <= 36) {
				if((y - 1) >= 0) {
					positions.add(getCurrentPosition((x + 2),(y - 1)));
				}
				if((y + 1) <= 36) {
					positions.add(getCurrentPosition((x + 2),(y + 1)));
				}
			}
			if((y - 2) >= 0) {
				if((x - 1) >= 0) {
					positions.add(getCurrentPosition((x - 1),(y - 2)));
				}
				if((x + 1) <= 36) {
					positions.add(getCurrentPosition((x + 1),(y - 2)));
				}
			}
			if((y + 2) <= 36) {
				if((x - 1) >= 0) {
					positions.add(getCurrentPosition((x - 1),(y + 2)));
				}
				if((x + 1) <= 36) {
					positions.add(getCurrentPosition((x + 1),(y + 2)));
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

	private int getCurrentPosition(int x,int y) {
		return (x + (y * 6));
	}
}
