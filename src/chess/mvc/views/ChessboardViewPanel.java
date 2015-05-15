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
	private IBoard board;

	@Override
	public void update(ChessEvent event) {
		if (event instanceof UpdateUIEvent) {
			this.setBoard(((UpdateUIEvent) event).getBoard());
		}
	}
	
	public ChessboardViewPanel(GameController handler) {
		this.actionHandler = handler;
		this.setBoard(actionHandler.getBoard());
		this.setBackground(Color.BLACK);
	}
	
	public void setBoard(IBoard board) {
		this.board = board;
	}

	private void initialComponent() {
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
		for (Component comp : this.components) {
			JButton btn = (JButton)comp;
			
			int x = (pos % board.getWidth());
			int y = (pos / board.getHeight());

			if ((y % 2 == 1 && x % 2 == 1) || (y % 2 == 0 && x % 2 == 0)) {
				btn.setBackground(Color.LIGHT_GRAY);
			} else {
				btn.setBackground(Color.GRAY);
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
			btn.setText(symbol);
			btn.addMouseListener(this.actionHandler.new PieceAction(pos));
			
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
