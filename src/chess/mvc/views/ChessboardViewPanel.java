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
import chess.prototype.observer.*;


public class ChessboardViewPanel extends JPanel implements IObserver {

	private static Font font = new Font("Sans-Serif", Font.PLAIN, 50);

	private Component[] components;
	private GameController actionHandler;

	@Override
	public void update(ChessEvent event) {
		if (event instanceof UpdateUIEvent) {
			this.redraw(false);
		}
	}
	
	public ChessboardViewPanel(GameController handler) {
		this.actionHandler = handler;
		this.setBackground(Color.BLACK);
	}

	private void initialComponent() {
		IBoard board = actionHandler.getBoard();
		this.setLayout(new GridLayout(board.getWidth(), board.getHeight()));
		this.setBorder(new LineBorder(Color.BLACK));
		
		int boardSize = board.getHeight() * board.getWidth();
		this.components = new Component[boardSize];
		for (int i = 0; i < boardSize; i++) {
			JComponent b = new JButton();
			this.components[i] = b;
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
		for (Component comp : this.components) {
			JButton btn = (JButton)comp;
			
			int x = (pos % board.getWidth());
			int y = (pos / board.getHeight());

			if ((y % 2 == 1 && x % 2 == 1) || (y % 2 == 0 && x % 2 == 0)) {
				btn.setBackground(Color.WHITE);
			} else {
				btn.setBackground(Color.BLACK);
			}

			Piece piece = board.getPiece(pos);
			String symbol = "";
					
			if (piece != null) {
				symbol = piece.getSymbol();	
				btn.setFont(font);
				//((AbstractButton) comp).setFont(font);
				
				if (piece.getOwner() != null) {
					btn.setForeground(piece.getOwner().getColour());
					// ((AbstractButton) comp).setForeground(piece.getOwner().getColour());
				}
			}	

			//btn.setText(pos + "");
			// try to hide the position inside action command
			btn.setActionCommand(pos + "");
			btn.setText(symbol);
			//btn.addMouseListener(this.actionHandler.new PieceAction(pos));
			btn.addMouseListener(new MouseHandler(pos, this.actionHandler));
			this.add(btn);
			pos++;
		}
		this.validate();
	}
	
	/*
	 * @pre.condition: this.components != null
	 */
	public void clearPath() {
		if (this.components == null) return;
		
		this.redraw();
	}
	
	public void markPath(int pos) {
		components[pos].setBackground(Color.RED);
	}

	/*
	 * @pre.condition: i between 0 to this.components.length
	 * @post.condition: return a null or an instance of the component object
	 */
	public Component getSquare(int i) {
		if (i < 0 || i > this.components.length) return null;
		return this.components[i];
	}
}
