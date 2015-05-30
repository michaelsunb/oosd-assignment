/*
 * AUTHOR: Sokun, CHORN
 * Number: S3455783
 */
package chess.core;

import java.util.*;
import java.awt.Color;
import java.io.Serializable;

public class Player implements Serializable {

	private Color colour;
	private int numberOfMove = 0;
	private Boolean turn = false;
	private int score = 0;

	public Player() {
	}

	public Boolean isTurn() {
		return turn;
	}

	public void setTurn(Boolean turn) {
		this.turn = turn;
	}

	public void move(int prevPos, int nextPos) {
		this.numberOfMove++;
	}

	public void setNumberOfMove(int numberOfMove) {
		this.numberOfMove = numberOfMove;
	}

	public int getNumberOfMove() {
		return numberOfMove;
	}

	public void increaseMove() {
		this.numberOfMove++;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color black) {
		this.colour = black;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int value) {
		if (value > 1 && value % 5 != 0) {
			return;
		}

		this.score += value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Player)) return false;
		return ((Player)obj).getColour().equals(this.colour);
	}
	
}
