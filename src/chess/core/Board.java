package chess.core;

import java.io.Serializable;

public class Board implements IBoard, Serializable {
	private int height;
	private int width;
	private Piece[] squares;

	public Board() {
		this.height = 6;
		this.width = 6;
	}

	public Board(int height, int width) {
		this.height = height;
		this.width = width;
	}

	@Override
	public void init() {
		int boardSize = height * width;
		squares = new Piece[boardSize];
	}

	@Override
	public Piece[] getPieces() {
		return squares;
	}

	@Override
	public Piece getPiece(int pos) {
		return squares[pos];
	}

	public void setPiece(int pos, Piece piece) {
		this.squares[pos]  = piece;
	}
	
	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	public boolean isSqureEmpty(int pos) {
		return (squares[pos] == null) ? true : false;
	}

	@Override
	public int getPiecePosition(Piece currentPiece) {
		for(int i = 0; i < this.squares.length; i++) {
			if(this.squares[i] != null &&
					this.squares[i].equals(currentPiece)) {
				return i;
			}
		}
		return -1;
	}
}
