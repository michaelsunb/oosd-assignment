package chess.core;

import java.io.Serializable;
import java.util.Arrays;

public abstract class Piece implements Serializable {
	protected int score = 0;
	private Player owner = null;
	/*
	 * Chess symbol in unicode
	 * https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode
	 */
	protected Character symbol = ' ';

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public abstract int[] getMovablePositions(int pos);

	/**
	 * @pre.condition: 2 integers passed
	 * 
	 * @post.condition: returns boolean indicator if piece is allowed to move to destination
	 */
	public boolean canMoveTo(int current, int destination) {
		int[] positions = this.getMovablePositions(current);
		Arrays.sort(positions);
		return Arrays.binarySearch(positions, destination) > -1;
	}
	
	public String getSymbol() {
		return this.symbol.toString();
	}
}
