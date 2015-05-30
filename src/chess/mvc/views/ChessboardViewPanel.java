package chess.mvc.views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import chess.core.*;
import chess.mvc.controllers.GameController;
import chess.mvc.models.GameStatusEvent;
import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.PieceSelectedEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.dnd.PieceDropTarget;
import chess.prototype.observer.*;


public class ChessboardViewPanel extends JPanel implements IObserver {
	private Square[] squares;
	private GameController actionHandler;

	@Override
	public void update(ChessEvent event) {
		if (event instanceof UpdateUIEvent) {
			this.redraw(false);
		}
	}
	
	public ChessboardViewPanel(GameController handler) {
		this.actionHandler = handler;
		//this.setBackground(Color.BLACK);
	}

	private void initialComponent() {
		IBoard board = actionHandler.getBoard();
		int w = board.getWidth();
		int h = board.getHeight();

		this.setLayout(new GridLayout(h, w));
		this.setBorder(new LineBorder(Color.BLACK));
		
		int boardSize = board.getHeight() * board.getWidth();
		this.squares = new Square[boardSize];
		
		for (int i = 0; i < boardSize; i++) {
			this.squares[i] = new Square(i);
			
			this.squares[i].addMouseListener(new MouseHandler(i, this.actionHandler));
			new PieceDropTarget(this.squares[i]);
		}
	}
	
	public void redraw() {
		this.redraw(false);
	}
	
	public void redraw(boolean clearView) {
		if (clearView) {
			this.removeAll();
			initialComponent();
		}

		int pos = 0;
		IBoard board = actionHandler.getBoard();
		for (Square square : this.squares) {
			
			int x = (pos % board.getWidth());
			int y = (pos / board.getHeight());

			if ((y % 2 == 1 && x % 2 == 1) || (y % 2 == 0 && x % 2 == 0)) {
				square.setBackground(Color.GRAY);
			} else {
				square.setBackground(Color.LIGHT_GRAY);
			}

			Piece piece = board.getPiece(pos);
					
			if (piece != null) {
				square.draw(piece);
			} else {
				square.empty();
			}
						
			this.add(square);
			pos++;
		}
		this.validate();
	}
	
	/*
	 * @pre.condition: this.components != null
	 */
	public void clearPath() {
		if (this.squares == null) return;
		
		this.redraw();
	}
	
	public void markPath(int pos) {
		squares[pos].setBackground(Color.RED);
	}

	/*
	 * @pre.condition: i between 0 to this.components.length
	 * @post.condition: return a null or an instance of the component object
	 */
	public Square getSquare(int i) {
		if (i < 0 || i > this.squares.length) return null;
		return this.squares[i];
	}
	
	public Square getSquareAt(int x, int y) {
		Component comp = this.getComponentAt(Math.abs(x), Math.abs(y));
		if (!(comp instanceof Square)) return null;
		
		for (int i = 0; i < this.squares.length; i++) {
			if (comp.equals(this.squares[i])) {
				System.out.println("position: "+ i);
				return this.squares[i];
			}
		}
		return null;
	}
}
