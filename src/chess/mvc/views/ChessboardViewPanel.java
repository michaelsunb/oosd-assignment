package chess.mvc.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

import chess.core.*;
import chess.prototype.observer.*;

/*
 * Adapted from http://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
 * TODO: inject board data into the view
 */
public class ChessboardViewPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3243088743876785303L;
	private static Font font = new Font("Sans-Serif", Font.PLAIN, 50);

	private IBoard board;

	private String setActionCommand = "pickPiece";
	private int prevPosition;
	private Piece prevPiece;
	
	public ChessboardViewPanel()
	{
		board = Game.getInstance().getBoardInstance();
		/*
		 * TODO: get it from board object
		 */

		this.setLayout(new GridLayout(board.getWidth(), board.getHeight()));

		this.setBorder(new LineBorder(Color.BLACK));
		
		
		renderBoard(initialComponenets());
        
        // fill the chess board
	}
	
	private Component[] initialComponenets() {
		int boardSize = board.getHeight() * board.getWidth();
		Component[] comp = new Component[boardSize];
		for(int i = 0; i <boardSize ; i++) {
			JComponent b = new JButton();
            comp[i] = b;
		}
		return (Component[])comp;
	}
    
    private void renderBoard(Component[] component) {
		int pos = 0;
		for (Component comp : component) {
			int x = (pos % board.getWidth());
			int y = (pos / board.getHeight());
			 if((y % 2 == 1 && x % 2 == 1) ||
					 (y % 2 == 0 && x % 2 == 0)){
				 comp.setBackground(Color.LIGHT_GRAY);
			 } else {
				 comp.setBackground(Color.GRAY);
			 }

			 Piece piece = board.getPiece(pos);
			 String symbol = "";
			 if(piece != null){
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

    class GameAction extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private int position;

		// This is our sample action. It must have an actionPerformed() method,
        // which is called when the action should be invoked.
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

            ChessEvent event = null;
            switch (e.getActionCommand()) {
	            case "pickPiece":
	            	event = new PieceMovesEvent(position, p, currentSource);
	            	setActionCommand = "movePiece";
	            	prevPosition = position;
	            	prevPiece = p;
	            	renderBoard(listComponents);
	    			ChessEventDispatcher.getInstance().fireEvent(event);
	                break;
	            case "movePiece":
	            	event = new PieceMovedEvent(prevPosition, position, prevPiece);
	            	setActionCommand = "pickPiece";
	    			ChessEventDispatcher.getInstance().fireEvent(event);
	            	renderBoard(listComponents);
	            	Game.getInstance().swapPlayer();
	            	break;
	        }
        }
    }
}
