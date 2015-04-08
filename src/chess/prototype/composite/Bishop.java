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
		ArrayList<Integer> positions = new ArrayList<Integer>();

		int boardSize = height * width;

		if(currPos >= 0 && currPos < boardSize) {

			int x = (currPos % width);
			positions.add(currPos);

			// For all down left
			int movable = currPos;
			int currX = x;
			boolean isLeft = true;
			while(isLeft) {
				movable += 5;
				int checkLeftX = movable % width;
				isLeft = (checkLeftX < currX) && (movable < boardSize);
				if(isLeft) {
					positions.add(movable);
					currX = checkLeftX;
				}
			}
			// For all up right
			movable = currPos;
			currX = x;
			boolean isRight = true;
			while(isRight) {
				movable -= 5;
				int checkRightX = movable % width;
				isRight = (checkRightX > currX) && (movable >= 0);
				if(isRight) {
					positions.add(movable);
					currX = checkRightX;
				}
			}
			// For all down right
			movable = currPos;
			currX = x;
			isRight = true;
			while(isRight) {
				movable += 7;
				int checkRightX = movable % width;
				isRight = (checkRightX > currX) && (movable < boardSize);
				if(isRight) {
					positions.add(movable);
					currX = checkRightX;
				}
			}
			// For all up left
			movable = currPos;
			currX = x;
			isLeft = true;
			while(isLeft) {
				movable -= 7;
				int checkLeftX = movable % width;
				isLeft = (checkLeftX < currX) && (movable >= 0);
				if(isLeft) {
					positions.add(movable);
					currX = checkLeftX;
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
