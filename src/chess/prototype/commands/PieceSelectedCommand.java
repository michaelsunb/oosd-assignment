package chess.prototype.commands;

import chess.core.*;
import chess.mvc.models.*;
import chess.mvc.views.ChessboardViewPanel;
import chess.prototype.composite.Barrier;
import chess.prototype.observer.*;

public class PieceSelectedCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!((event instanceof PieceSelectedEvent))) return;
		
		PieceSelectedEvent selEvent = (PieceSelectedEvent)event;
		
		Piece piece = this.getBoard().getPiece(selEvent.getPosition());
		
		if (piece == null || (piece instanceof Barrier)) {
			return;
		}
		
		// not your piece mate :P
		if (null != piece.getOwner() 
				&& !piece.getOwner().equals(getGame().getCurrentPlayer())) {
			return;
		}
		
		this.getGame().setSelectedPiece(piece);
		
		ChessboardViewPanel chessPane = selEvent.getMainFrame().getChessBoardPane();
		int currPos = selEvent.getPosition();
		
		int movablePos[] = piece.getMovablePositions(currPos);
		
		chessPane.clearPath();
		
		for (int pos : movablePos) {
			chessPane.markPath(pos);
		}

		selEvent.getMainFrame().getPieceViewPane().setSelectPiece(piece);
		
	}

}
