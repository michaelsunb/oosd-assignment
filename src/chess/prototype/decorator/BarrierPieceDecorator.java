package chess.prototype.decorator;

import chess.core.IBoard;
import chess.core.Piece;
import chess.prototype.composite.Barrier;

public class BarrierPieceDecorator extends BoardDecorator {

	public BarrierPieceDecorator(IBoard board) {
		super(board);
	}

	@Override
	public void init() {
		for(int i = 12; i < 23; i++) {
			this.board.getPieces()[i] = new Barrier();
		}
	}

}
