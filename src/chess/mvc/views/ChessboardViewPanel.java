package chess.mvc.views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import chess.core.*;
import chess.prototype.events.*;


public class ChessboardViewPanel extends JPanel {

	private static Font font = new Font("Sans-Serif", Font.PLAIN, 50);
	private String setActionCommand = "pickPiece";	
	private int prevPosition;
	private Component[] components;
	private AbstractAction actionHandler;
	
	public ChessboardViewPanel(AbstractAction handler) {
		this.actionHandler = handler;
	}

	private void initialComponent() {
		IBoard board = Game.getInstance().getBoardInstance();
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
		
		IBoard board = Game.getInstance().getBoardInstance();
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
			
			btn.setText(symbol);

			/*
			 * TODO: What if we build a special format string & use it as actionCommand?
			 * e.g PieceSelectedEvent 1; then have a command parser which turn this string into ChessEvent?
			 */
			btn.setActionCommand("PieceSelectedEvent:" +  pos);
			btn.addActionListener(this.actionHandler);
			
			this.add(btn);
			pos++;
		}
		this.validate();
	}
	
	public void clearPath() {
		for(Component comp: this.components) {
			if (comp.getBackground() == Color.RED) {
				// what is the default color
				comp.setBackground(Color.GRAY);
			}
		}
	}
	
	public void markPath(int pos) {
		components[pos].setBackground(Color.RED);
	}
	
	/*
	 * Sokun's comment: refactor to use Observer pattern instead of extending
	 * AbstractAction
	 */
	class GameAction extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private int position;

		public GameAction(String title, String command, int position) {
			super(title);
			this.position = position;
			putValue(ACTION_COMMAND_KEY, command);
			setActionCommand = command;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Component currentSource = ((Component) e.getSource());
			Component[] listComponents = currentSource.getParent().getComponents();
			Piece p = Game.getInstance().getBoardInstance().getPiece(position);

			ChessEventDispatcher eventMgr = ChessEventDispatcher.getInstance();

			switch (e.getActionCommand()) {
			case "pickPiece":
				if (p == null || p != null && p.getOwner() == null || p != null
						&& p.getOwner() != null
						&& p.getOwner() != Game.getInstance().getCurrentPlayer()) {
					break;
				}
				setActionCommand = "movePiece";

				// fire two events
				int numOfMoves = Game.getInstance().getMaxMoves();
//				ChessEvent eventStatus = new GameStatusEvent(p.getOwner(), numOfMoves);
//				ChessEvent eventSelect = new PieceSelectedEvent(position, currentSource);

				redraw(false);
//				eventMgr.fireEvent(eventStatus);
//				eventMgr.fireEvent(eventSelect);
				
				prevPosition = position;
				break;
			case "movePiece":
				setActionCommand = "pickPiece";

				ChessEvent movePieceEvent = new PieceMovedEvent(position,prevPosition);
				eventMgr.fireEvent(movePieceEvent);

				redraw();
				break;
			}
		}
	}

}
