/*
 * AUTHOR: Sokun, CHORN
 * Number: S3455783
 */
package chess.core;

import java.awt.Color;
import java.io.Serializable;

public class Player implements Serializable {

	private Color colour;
	private int numberOfMove = 0;
	private Boolean turn = false;
	private int score = 0;

	/**
	 * @pre.condition: Must set a boolean to private turn property 
	 * @post.condition: returns true/false if it's player's turn
	 */
	public Boolean isTurn() {
		return turn;
	}

	/**
	 * @pre.condition: Input boolean value 
	 * @post.condition: Stores whether or not it is the player's turn
	 */
	public void setTurn(Boolean turn) {
		this.turn = turn;
	}

	/**
	 * @pre.condition: Input integer value 
	 * @post.condition: Sets the number of moves left
	 */
	public void setNumberOfMove(int numberOfMove) {
		this.numberOfMove = numberOfMove;
	}

	/**
	 * @pre.condition: Must set a integer to private numberOfMove property 
	 * @post.condition: returns an integer value of the number of moves
	 */
	public int getNumberOfMove() {
		return numberOfMove;
	}

	/**
	 * @pre.condition: Integer value in private numberOfMove property must be set 
	 * @post.condition: increases the number of moves by 1
	 */
	public void increaseMove() {
		this.numberOfMove++;
	}

	/**
	 * @pre.condition: Private color property must be set 
	 * @post.condition: Returns the colour of the piece
	 */
	public Color getColour() {
		return colour;
	}

	/**
	 * @pre.condition: Input java.awt.Color
	 * @post.condition: Stores the colour of the piece
	 */
	public void setColour(Color colour) {
		this.colour = colour;
	}

	/**
	 * @pre.condition: Private int score must have an integer value
	 * @post.condition: Returns how much score the player has.
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * @pre.condition: Input an integer value
	 * @post.condition: Stores the player's score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @pre.condition: Input an integer value
	 * @post.condition: Add's to score given from the input
	 */
	public void addScore(int value) {
		if (value > 1 && value % 5 != 0) {
			return;
		}

		this.score += value;
	}

	/**
	 * @pre.condition: Input value of preferably a Player
	 * @post.condition: Returns if this player is the same colour
	 * of the input Player
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Player)) return false;
		return ((Player)obj).getColour().equals(this.colour);
	}
}
