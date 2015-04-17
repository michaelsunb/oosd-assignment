package chess.prototype.decorator;

import chess.core.IBoard;
import chess.prototype.composite.Barrier;

public class BarrierPieceDecorator extends BoardDecorator {
	final private static int MID_UPPER_BOUND = 23;
	final private static int MID_LOWER_BOUND = 12;

	public BarrierPieceDecorator(IBoard board) {
		super(board);
	}

	/*
	 * Placing Barrier object in the middle of the chess board
	 * 
	 * @see chess.prototype.decorator.BoardDecorator#init()
	 */
	@Override
	public void init() {
		for (int i = MID_LOWER_BOUND; i <= MID_UPPER_BOUND; i++) {
			this.board.getPieces()[i] = new Barrier();
		}
	}
}
