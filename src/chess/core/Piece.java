package chess.core;

import java.util.Arrays;

public abstract class Piece {
	protected int score = 0;
	private Player owner;
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

	public boolean canMoveTo(int current, int destination) {
		return Arrays.binarySearch(this.getMovablePositions(current), destination) >= -1;
	}
	
	public String getSymbol() {
		return this.symbol.toString();
	}
}
