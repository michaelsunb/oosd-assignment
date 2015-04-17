package chess.prototype.decorator;

import chess.core.IBoard;
import chess.core.Piece;

public abstract class BoardDecorator implements IBoard {
	protected IBoard board;

	public BoardDecorator(IBoard board) {
		this.board = board;
	}

	@Override
	public abstract void init();

	@Override
	public Piece[] getPieces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Piece getPiece(int pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight() {
		return this.board.getHeight();
	}

	@Override
	public int getWidth() {
		return this.board.getWidth();
	}
}
