package chess.core;

import java.util.*;

public class Player {
	private List<Piece> pieces;
	private int numberOfMove = 0;
	private Boolean turn = false;
	
	public Boolean getTurn() {
		return turn;
	}

	public void setTurn(Boolean turn) {
		this.turn = turn;
	}

	public Player()
	{
		pieces = new ArrayList<Piece>();
	}
	
	public void move(int prevPos, int nextPos) {
		/*
		this.prevPos = prevPos;
		this.nextPos = nextPos;
		swapPlayer();
		positionsChanged();
		*/
		this.numberOfMove ++;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public int getNumberOfMove() {
		return numberOfMove;
	}

	public void setNumberOfMove(int numberOfMove) {
		this.numberOfMove = numberOfMove;
	}
	
}
