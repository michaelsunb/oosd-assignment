package chess.prototype.composite;

import java.util.ArrayList;

import chess.core.Game;
import chess.core.IBoard;
import chess.core.Piece;
import chess.piece.commands.ChessPieceMoveCheck;

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

		IBoard board = Game.getInstance().getBoardInstance();
		
		int boardSize = board.getHeight() * board.getWidth();
		int width = board.getWidth();
		int height = board.getHeight();

		if(currPos >= 0 && currPos < boardSize) {
			// For all y
			int checkSame = -1;
			for(int i=0;i<=boardSize;i++) {
				int x = (currPos % width);
				int y = (i / height);
				int movable = x + (y * height);
				
				if(checkSame != movable && movable < boardSize && movable != currPos) {
					positions.add(movable);
					if(ChessPieceMoveCheck.checkTargetSquareIfEmpty(movable)){
						checkSame = movable;
					}
					else{
						break;
					}
				}
				
			}

			// For all x
			for(int i=0;i<width;i++) {
				int y = (currPos / width);
				int movable = i + y + (y * (width-1));
				if(movable < boardSize && movable != currPos) {
					positions.add(movable);
					if(!ChessPieceMoveCheck.checkTargetSquareIfEmpty(movable)){
						break;
					}
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
