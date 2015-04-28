package chess.core;

import java.io.Serializable;

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

	public String getSymbol() {
		return this.symbol.toString();
	}
}
