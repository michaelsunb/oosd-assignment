package chess.mvc.views;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import chess.core.Game;
import chess.core.IBoard;
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
		this.fireSelectEvent(); // fire select event first

		Game game = Game.getInstance();
		Piece piece = game.getSelectedPiece();
		if (piece == null) {
			return;
		}
		
		int current = game.getBoardInstance().getPiecePosition(piece);
		if(current == -1) {
			return;
		}
		
		PieceViewPanel pvp = controller.getView().getPieceViewPane();
		if (!pvp.needSplit()) {
			return;
		}
		
		Piece seletecPiece = pvp.getSelectedPieces();

		boolean canMoveTo = seletecPiece.canMoveTo(current, this.position);
		if(!canMoveTo) {
			return;
		}

		ChessEvent event = new PieceSplitEvent((CombinePiece)piece, 
				seletecPiece, this.position);
		pvp.setSelectPiece(seletecPiece);

		ChessEventDispatcher.getInstance().fireEvent(event);
	}

	public void mouseReleased(MouseEvent e) {
		int nextPosition = calcPosition(e);

		PieceViewPanel pvp = controller.getView().getPieceViewPane();

		Game game = Game.getInstance();
		Piece piece = game.getSelectedPiece();
		if (piece != null) {
			int current = game.getBoardInstance().getPiecePosition(piece);
			if (current == -1) return;
			if (pvp.needSplit()) return;
			if (!piece.canMoveTo(current, nextPosition)) return;
		}

		fireMoveEvent(nextPosition);
	}

	private int calcPosition(MouseEvent e) {
		IBoard board = Game.getInstance().getBoardInstance();
		int x = (position % board.getWidth());
		int y = (position / board.getWidth());

		int newY = (y + e.getY() / 100);
		if ((y + e.getY() % 100) < 0) {
			newY--;
		}

		int newX = (x + e.getX() / 100);
		if ((x + e.getX() % 100) < 0) {
			newX--;
		}
		return (newX + (newY * board.getHeight()));
	}
	
	private void fireSelectEvent() {
		ChessEvent event = new PieceSelectedEvent(this.position, controller.getView());
		ChessEventDispatcher.getInstance().fireEvent(event);
	}
	
	private void fireMoveEvent(int nextPosition) {
		ChessEvent event = new PieceMovedEvent(this.position, nextPosition);
		ChessEventDispatcher.getInstance().fireEvent(event);
	}
}