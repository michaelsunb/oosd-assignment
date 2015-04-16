package chess.prototype.composite;

import java.util.ArrayList;

import chess.core.Board;
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

		Board board = (Board) Game.getInstance().getBoardInstance();
		
		int boardSize = board.getHeight() * board.getWidth();
		int width = board.getWidth();
		int height = board.getHeight();

		if(currPos >= 0 && currPos < boardSize) {
			int x = (currPos % width);
			int y = (currPos / height);
			int movable = y;
			int currX = x;
			int currY = y;

			// For all north
			boolean isNorth = true;
			while(isNorth) {
				movable -= 1;
				isNorth = (movable < currY) && (movable >= 0);
				if(isNorth) {
					if(currPos == movable){
						continue;
					}
					
					int currMovablePos = getCurrentPosition(x,movable);
					positions.add(currMovablePos);
					if(!board.checkTargetSquareIfEmpty(currMovablePos))
						break;
				}
			}

			// for all south
			movable = y;
			currY = y;
			boolean isSouth = true;
			while(isSouth) {
				movable += 1;
				isSouth = (movable > currY) && (movable < board.getHeight());
				if(isSouth) {
					if(currPos == movable){
						continue;
					}
					int currMovablePos = getCurrentPosition(x,movable);
					positions.add(currMovablePos);
					if(!board.checkTargetSquareIfEmpty(currMovablePos))
						break;
				}
			}

			// for all east
			movable = x;
			currX = x;
			boolean isEast = true;
			while(isEast) {
				movable += 1;
				isEast = (movable > currX) && (movable < board.getHeight());
				if(isEast) {
					if(currPos == movable){
						continue;
					}
					int currMovablePos = getCurrentPosition(movable,y);
					positions.add(currMovablePos);
					if(!board.checkTargetSquareIfEmpty(currMovablePos))
						break;
				}
			}

			// for all west
			movable = x;
			currX = x;
			boolean isWest = true;
			while(isWest) {
				movable -= 1;
				isWest = (movable < currX) && (movable >= 0);
				if(isWest) {
					if(currPos == movable){
						continue;
					}
					int currMovablePos = getCurrentPosition(movable,y);
					positions.add(currMovablePos);
					if(!board.checkTargetSquareIfEmpty(currMovablePos))
						break;
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
		return (x + (y * Game.getInstance().getBoardInstance().getHeight()));
	}
}
