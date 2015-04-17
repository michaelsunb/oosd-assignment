package chess.mvc.views;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import chess.core.*;
import chess.prototype.observer.*;

/*
 * Adapted from http://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
 * TODO: inject board data into the view
 */
public class ChessboardViewPanel extends JPanel {
	private static Font font = new Font("Sans-Serif", Font.PLAIN, 50);
	private IBoard board;
	private String setActionCommand = "pickPiece";
	
	public ChessboardViewPanel()
	{
		board = Game.getInstance().getBoardInstance();

		this.setLayout(new GridLayout(board.getWidth(), board.getHeight()));

		this.setBorder(new LineBorder(Color.BLACK));

		renderBoard(initialComponenets());
	}
	
	private Component[] initialComponenets() {
		int boardSize = board.getHeight() * board.getWidth();
		Component[] comp = new Component[boardSize];
		for(int i = 0; i <boardSize ; i++) {
			JComponent b = new JButton();
            comp[i] = b;
		}
		return comp;
	}
    
    private void renderBoard(Component[] component) {
		int pos = 0;
		for (Component comp : component) {
			int x = (pos % board.getWidth());
			int y = (pos / board.getHeight());
			/*
			 * Sokun's comment: need refactoring
			 */
			if((y % 2 == 1 && x % 2 == 1) ||
					(y % 2 == 0 && x % 2 == 0)){
				comp.setBackground(Color.LIGHT_GRAY);
			} else {
				comp.setBackground(Color.GRAY);
			}

			Piece piece = board.getPiece(pos);
			String symbol = "";
			if(piece != null) {
				symbol = board.getPiece(pos).getSymbol();

				((AbstractButton) comp).setFont(font);
				if(piece.getOwner() != null) {
					((AbstractButton) comp).setForeground(piece.getOwner().getColour());
				}
			}
			((AbstractButton) comp).setAction(new GameAction(symbol, setActionCommand, pos));
			this.add(comp);
			pos++;
		}
    }

    /*
     * Sokun's comment: refactor to use Observer pattern instead of
     * extending AbstractAction
     */
    class GameAction extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private int position;

        public GameAction(String title, String command,int position) {
        	super(title);
        	this.position = position;
        	putValue(ACTION_COMMAND_KEY, command);
        	setActionCommand = command;
        }

        public void actionPerformed(ActionEvent e) {
        	Component currentSource = ((Component) e.getSource());
    		Component[] listComponents = currentSource.getParent().getComponents();
        	Piece p = Game.getInstance().getBoardInstance().getPiece(position);

        	ChessEventDispatcher eventMgr = ChessEventDispatcher.getInstance();

            switch (e.getActionCommand()) {
	            case "pickPiece":
	    			if(p == null ||
	    			p != null &&
	    			p.getOwner() == null ||
	    			p != null &&
	    			p.getOwner() != null &&
	    			p.getOwner() != Game.getInstance().getCurrentPlayer()) {
	    				break;
	    			}
	            	setActionCommand = "movePiece";

	            	// fire two events
	    			int numOfMoves = Game.getInstance().getMaxMoves();
	            	ChessEvent eventStatus = new GameStatusEvent(p.getOwner(), numOfMoves);
	            	ChessEvent eventSelect = new PieceSelectedEvent(position, p, currentSource);
	            	
	            	renderBoard(listComponents);
	            	eventMgr.fireEvent(eventStatus);
	            	eventMgr.fireEvent(eventSelect);
	                break;
	            case "movePiece":
	            	setActionCommand = "pickPiece";
	            	
	            	ChessEvent movePieceEvent = new PieceMovedEvent(position);
	            	eventMgr.fireEvent(movePieceEvent);
	    			
	            	renderBoard(listComponents);
	            	break;
	        }
        }
    }
}
