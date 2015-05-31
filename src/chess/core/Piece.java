package chess.core;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @see chess.prototype.composite package
 */
public abstract class Piece implements Serializable {
	protected int score = 0;
	private Player owner = null;
	/*
	 * Chess symbol in unicode
	 * https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode
	 */
	protected Character symbol = ' ';

	/**
	 * @pre.condition: Private property of owner must be set with an player 
	 * @post.condition: Returns the owner of said piece
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @pre.condition: Input value of a player class
	 * @post.condition: Sets said piece to a player
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * @pre.condition: Private property of score must be set with an integer 
	 * @post.condition: Returns the score of said piece
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @pre.condition: Input value of an integer
	 * @post.condition: sets the score of said piece
	 */
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

	/**
	 * @pre.condition: Child class must set a Character to the
	 * protected property "symbol"
	 * @post.condition: returns protected property "symbol" as a string
	 */
	public String getSymbol() {
		return this.symbol.toString();
	}
}
