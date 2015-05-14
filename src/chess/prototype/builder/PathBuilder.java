package chess.prototype.builder;

import java.util.ArrayList;
import java.util.Random;

import chess.core.Board;
import chess.core.Game;

public class PathBuilder {
	private final int[] movablePositions;

	private PathBuilder(Builder build) {
		movablePositions = new int[build.positions.size()];
		int i = 0;
		for (Integer pos : build.positions) {
			movablePositions[i++] = pos;
		}
	}
	
	public int[] getMovablePositions() {
		return movablePositions;
	}

	public static class Builder {
		private final int boardSize;
		private final int currPos;
		
		private final int currX;
		private final int currY;

		private static Board board;
		private int movable;
		
		private ArrayList<Integer> positions = new ArrayList<Integer>();

		public Builder(int currentPosition) throws Exception {
			board = (Board) Game.getInstance().getBoardInstance();
			boardSize = board.getHeight() * board.getWidth();
			currPos = currentPosition;

			if (currPos < 0 || currPos > boardSize) {
				throw new Exception();
			}

			currX = (currPos % board.getWidth());
			currY = (currPos / board.getWidth());
		}

		public PathBuilder build() {
			return new PathBuilder(this);
		}
		
		public Builder north() {
			movable = currY;
			return setDirection(0,-1);
		}
		
		public Builder south() {
			movable = currY;
			return setDirection(1,1);
		}
		
		public Builder east() {
			movable = currX;
			return setDirection(2,1);
		}
		
		public Builder west() {
			movable = currX;
			return setDirection(3,-1);
		}
		
		public Builder northEast() {
			movable = currPos;
			return setDirection(4,-(board.getWidth() - 1));
		}
		
		public Builder northWest() {
			movable = currPos;
			return setDirection(5,-(board.getWidth() + 1));
		}
		
		public Builder southEast() {
			movable = currPos;
			return setDirection(6, (board.getWidth() + 1));
		}
		
		public Builder southWest() {
			movable = currPos;
			return setDirection(7, (board.getWidth() - 1));
		}
		
		public Builder northTwoEastOne() {
			if ((currY + 2) < board.getHeight()) {
				if ((currX + 1) < board.getWidth()) {
					positions.add(getCurrentPosition((currX + 1), (currY + 2)));
				}
			}
			return this;
		}
		
		public Builder northTwoWestOne() {
			if ((currY + 2) < board.getHeight()) {
				if ((currX - 1) < board.getWidth()) {
					positions.add(getCurrentPosition((currX - 1), (currY + 2)));
				}
			}
			return this;
		}
		
		public Builder southTwoEastOne() {
			if ((currY - 2) >= 0) {
				if ((currX + 1) < board.getWidth()) {
					positions.add(getCurrentPosition((currX + 1), (currY - 2)));
				}
			}
			return this;
		}
		
		public Builder southTwoWestOne() {
			if ((currY - 2) >= 0) {
				if ((currX - 1) >= 0) {
					positions.add(getCurrentPosition((currX - 1), (currY - 2)));
				}
			}
			return this;
		}
		
		public Builder eastTwoNorthOne() {
			if ((currX + 2) < board.getWidth()) {
				if ((currY + 1) < board.getHeight()) {
					positions.add(getCurrentPosition((currX + 2), (currY + 1)));
				}
			}
			return this;
		}
		
		public Builder eastTwoSouthOne() {
			if ((currX + 2) < board.getWidth()) {
				if ((currY - 1) >= 0) {
					positions.add(getCurrentPosition((currX + 2), (currY - 1)));
				}
			}
			return this;
		}
		
		public Builder westTwoNorthOne() {
			if ((currX - 2) >= 0) {
				if ((currY + 1) < board.getHeight()) {
					positions.add(getCurrentPosition((currX - 2), (currY + 1)));
				}
			}
			return this;
		}
		
		public Builder westTwoSouthOne() {
			if ((currX - 2) >= 0) {
				if ((currY - 1) >= 0) {
					positions.add(getCurrentPosition((currX - 2), (currY - 1)));
				}
			}
			return this;
		}

		public Builder randomPosition() {
		    Random rand = new Random();
		    positions.add(rand.nextInt((boardSize - 0) + 1) + 0);
		    return this;
		}
		
		private Builder setDirection(int direction,int addOrMinus) {
			int directionX;
			int x = currX;
			int y = currY;
			boolean isDirection = true;
			while(isDirection) {
				this.movable += addOrMinus;
				switch(direction) {
				case 0:
					isDirection = (movable < currY) && (movable >= 0);
					y = movable;
					break;
				case 1:
					isDirection = (movable > currY) && (movable < board.getHeight());
					y = movable;
					break;
				case 2:
					isDirection = (movable > currX) && (movable < board.getWidth());
					x = movable;
					break;
				case 3:
					isDirection = (movable < currX) && (movable >= 0);
					x = movable;
					break;
				case 4:
					directionX = movable % board.getWidth();
					isDirection = (directionX > x) && (movable >= 0);

					if(isDirection) {
						x = directionX;
						y = (movable / board.getWidth());
					}
					break;
				case 5:
					directionX = movable % board.getWidth();
					isDirection = (directionX < x) && (movable >= 0);

					if(isDirection) {
						x = directionX;
						y = (movable / board.getWidth());
					}
					break;
				case 6:
					directionX = movable % board.getWidth();
					isDirection = (directionX > x) && (movable < boardSize);

					if(isDirection) {
						x = directionX;
						y = (movable / board.getWidth());
					}
					break;
				case 7:
					directionX = movable % board.getWidth();
					isDirection = (directionX < x) && (movable < boardSize);

					if(isDirection) {
						x = directionX;
						y = (movable / board.getWidth());
					}
					break;
				}

				if(setAndFinish(isDirection, x, y))
					break;
			}
			return this;
		}

		private boolean setAndFinish(boolean returnLater, int x, int y) {
			if(!returnLater) return false; // return now and set to false 
										   // because we don't want to break
			int currMovablePos = getCurrentPosition(x,y);
			
			if (currPos == currMovablePos) return false;
			
			positions.add(currMovablePos);
			if (!board.isSqureEmpty(currMovablePos))
				return true;
			return false;
		}

		private int getCurrentPosition(int x, int y) {
			return (x + (y * Game.getInstance().getBoardInstance().getHeight()));
		}
	}
}
