package chess.prototype.commands;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;

import chess.core.*;
import chess.mvc.models.*;
import chess.mvc.views.ChessboardViewPanel;
import chess.prototype.composite.Barrier;
import chess.prototype.dnd.PieceDragGesture;
import chess.prototype.dnd.PieceDropTarget;
import chess.prototype.observer.*;

public class PieceSelectedCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof PieceSelectedEvent)
				|| Game.getInstance().isGameOver()) return;
		
		PieceSelectedEvent selEvent = (PieceSelectedEvent)event;
		
		Piece piece = this.getBoard().getPiece(selEvent.getPosition());
		// any selected piece?
		this.oldPosition = Game.getInstance().getSelPosition();
		Piece currentPiece = Game.getInstance().getBoardInstance().getPiece(this.oldPosition);
				
		if (piece == null 
				// author: Sokun
				// don't need to fire twice 
				|| piece == currentPiece 
				|| (piece instanceof Barrier)) {
			return;
		}
		
		// not your piece mate :P
		if (null != piece.getOwner() 
				&& !piece.getOwner().equals(getGame().getCurrentPlayer())) {
			return;
		}

		this.getGame().setSelPosition(selEvent.getPosition());
		selEvent.getMainFrame().getPieceViewPane().setSelectPiece(piece);
		
		ChessboardViewPanel chessPane = selEvent.getMainFrame().getChessBoardPane();		
		int currPos = selEvent.getPosition();
		
		// setup drag & drop
		DragSource ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(chessPane.getSquare(currPos),
				DnDConstants.ACTION_COPY, new PieceDragGesture());
		
		int movablePos[] = piece.getMovablePositions(currPos);
		
		chessPane.clearPath();
		
		for (int pos : movablePos) {
			// set drop target
			new PieceDropTarget(chessPane.getSquare(pos));
			// mark path
			chessPane.markPath(pos);
		}
		
	}

}
