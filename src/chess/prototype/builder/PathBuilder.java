package chess.prototype.builder;

import java.util.ArrayList;

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
		private enum Direction {
			NORTH, SOUTH, EAST, WEST,
			NORTHEAST, NORTHWEST,
			SOUTHEAST, SOUTHWEST,
			NORTH_TWO_EAST_ONE,
			NORTH_TWO_WEST_ONE,
			SOUTH_TWO_EAST_ONE,
			SOUTH_TWO_WEST_ONE,
			EAST_TWO_NORTH_ONE,
			EAST_TWO_SOUTH_ONE,
			WEST_TWO_NORTH_ONE,
			WEST_TWO_SOUTH_ONE
		}

		private final int boardSize;
		private final int currPos;
		
		private final int currX;
		private final int currY;

		private static Board board;
		
		private ArrayList<Integer> positions = new ArrayList<Integer>();

		public Builder(int currentPosition) throws Exception {
			board = (Board) Game.getInstance().getBoardInstance();
			boardSize = board.getHeight() * board.getWidth();
			currPos = currentPosition;

			if (currPos < 0 || currPos > boardSize) {
				throw new Exception("Current position is out of the board");
			}

			currX = (currPos % board.getWidth());
			currY = (currPos / board.getWidth());
		}

		public PathBuilder build() {
			return new PathBuilder(this);
		}
		
		public Builder north() {
			return setDirection(Direction.NORTH,-1);
		}
		
		public Builder south() {
			return setDirection(Direction.SOUTH,1);
		}
		
		public Builder east() {
			return setDirection(Direction.EAST,1);
		}
		
		public Builder west() {
			return setDirection(Direction.WEST,-1);
		}
		
		public Builder northEast() {
			return setDirection(Direction.NORTHEAST,-(board.getWidth() - 1));
		}
		
		public Builder northWest() {
			return setDirection(Direction.NORTHWEST,-(board.getWidth() + 1));
		}
		
		public Builder southEast() {
			return setDirection(Direction.SOUTHEAST, (board.getWidth() + 1));
		}
		
		public Builder southWest() {
			return setDirection(Direction.SOUTHWEST, (board.getWidth() - 1));
		}

		public Builder northTwoEastOne() {
			if ((currY + 2) < board.getHeight()) {
				if ((currX + 1) < board.getWidth()) {
					setAndFinish(true,(currX + 1), (currY + 2));
				}
			}
			return this;
		}
		
		public Builder northTwoWestOne() {
			if ((currY + 2) < board.getHeight()) {
				if ((currX - 1) < board.getWidth()) {
					setAndFinish(true,(currX - 1), (currY + 2));
				}
			}
			return this;
		}
		
		public Builder southTwoEastOne() {
			if ((currY - 2) >= 0) {
				if ((currX + 1) < board.getWidth()) {
					setAndFinish(true,(currX + 1), (currY - 2));
				}
			}
			return this;
		}
		
		public Builder southTwoWestOne() {
			if ((currY - 2) >= 0) {
				if ((currX - 1) >= 0) {
					setAndFinish(true,(currX - 1), (currY - 2));
				}
			}
			return this;
		}
		
		public Builder eastTwoNorthOne() {
			if ((currX + 2) < board.getWidth()) {
				if ((currY + 1) < board.getHeight()) {
					setAndFinish(true,(currX + 2), (currY + 1));
				}
			}
			return this;
		}
		
		public Builder eastTwoSouthOne() {
			if ((currX + 2) < board.getWidth()) {
				if ((currY - 1) >= 0) {
					setAndFinish(true,(currX + 2), (currY - 1));
				}
			}
			return this;
		}
		
		public Builder westTwoNorthOne() {
			if ((currX - 2) >= 0) {
				if ((currY + 1) < board.getHeight()) {
					setAndFinish(true,(currX - 2), (currY + 1));
				}
			}
			return this;
		}
		
		public Builder westTwoSouthOne() {
			if ((currX - 2) >= 0) {
				if ((currY - 1) >= 0) {
					setAndFinish(true,(currX - 2), (currY - 1));
				}
			}
			return this;
		}
		
		private Builder setDirection(Direction direction,int addOrMinus) {
			int movable;
			switch(direction) {
			case NORTH:
			case SOUTH:
				movable = currY;
				break;
			case EAST:
			case WEST:
				movable = currX;
				break;
			default:
				movable = currPos;
				break;
			}
			
			int directionX;
			int x = currX;
			int y = currY;
			boolean isDirection = true;
			while(isDirection) {
				movable += addOrMinus;
				switch(direction) {
				case NORTH:
					isDirection = (movable < currY) && (movable >= 0);
					y = movable;
					break;
				case SOUTH:
					isDirection = (movable > currY) && (movable < board.getHeight());
					y = movable;
					break;
				case EAST:
					isDirection = (movable > currX) && (movable < board.getWidth());
					x = movable;
					break;
				case WEST:
					isDirection = (movable < currX) && (movable >= 0);
					x = movable;
					break;
				case NORTHEAST:
					directionX = movable % board.getWidth();
					isDirection = (directionX > x) && (movable >= 0);

					if(isDirection) {
						x = directionX;
						y = (movable / board.getWidth());
					}
					break;
				case NORTHWEST:
					directionX = movable % board.getWidth();
					isDirection = (directionX < x) && (movable >= 0);

					if(isDirection) {
						x = directionX;
						y = (movable / board.getWidth());
					}
					break;
				case SOUTHEAST:
					directionX = movable % board.getWidth();
					isDirection = (directionX > x) && (movable < boardSize);

					if(isDirection) {
						x = directionX;
						y = (movable / board.getWidth());
					}
					break;
				case SOUTHWEST:
					directionX = movable % board.getWidth();
					isDirection = (directionX < x) && (movable < boardSize);

					if(isDirection) {
						x = directionX;
						y = (movable / board.getWidth());
					}
					break;
				default:
					return this;
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
			if (positions.contains(currMovablePos)) return false;
			
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
