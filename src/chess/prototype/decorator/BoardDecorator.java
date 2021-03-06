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
		return this.board.getPieces();
	}

	@Override
	public Piece getPiece(int pos) {
		return this.board.getPiece(pos);
	}

	@Override
	public void setPiece(int pos, Piece piece) {
		this.board.setPiece(pos, piece);
	}
	
	@Override
	public int getHeight() {
		return this.board.getHeight();
	}

	@Override
	public int getWidth() {
		return this.board.getWidth();
	}
	
	@Override
	public boolean isSqureEmpty(int pos) {
		return this.board.isSqureEmpty(pos);
	}

	@Override
	public int getPiecePosition(Piece currentPiece) {
		return this.board.getPiecePosition(currentPiece);
	}
	
	public void reset(int height, int width) {
		this.board.reset(height, width);
	}
}
