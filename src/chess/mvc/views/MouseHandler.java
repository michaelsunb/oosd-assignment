package chess.mvc.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import chess.core.Game;
import chess.core.Piece;
import chess.mvc.controllers.GameController;
import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.PieceSelectedEvent;
import chess.mvc.models.PieceSplitEvent;
import chess.prototype.composite.CombinePiece;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;

public class MouseHandler extends MouseAdapter {
	private int position;
	private GameController controller;
	
	public MouseHandler(int pos, GameController controller) {
		this.position = pos;
		this.controller = controller;
	}

	public void mousePressed(MouseEvent e) {
		ChessEvent event = null;

		if (SwingUtilities.isRightMouseButton(e)) {
			Piece piece = Game.getInstance().getSelectedPiece();
			if (piece == null) return;
			
			int current = Game.getInstance().getBoardInstance().getPiecePosition(piece);
			
			if (!piece.canMoveTo(current, this.position)) return;
			PieceViewPanel pvp = controller.getView().getPieceViewPane();

			if (pvp.needSplit()) {
				event = new PieceSplitEvent((CombinePiece)piece, 
						pvp.getSelectedPieces(), this.position);
			} else {
				event = new PieceMovedEvent(current, position);
			}
			
		} else if (SwingUtilities.isLeftMouseButton(e)) {
			event = new PieceSelectedEvent(this.position, controller.getView());
		}
		
		if (event == null) return;
		ChessEventDispatcher.getInstance().fireEvent(event);
	}

}