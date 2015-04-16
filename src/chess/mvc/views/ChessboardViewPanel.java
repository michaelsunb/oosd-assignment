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
	
	private int prevPosition;
	
	public ChessboardViewPanel()
	{
		board = Game.getInstance().getBoardInstance();
		/*
		 * TODO: get it from board object
		 */

		this.setLayout(new GridLayout(board.getWidth(), board.getHeight()));

		this.setBorder(new LineBorder(Color.BLACK));
		
		renderBoard(board.getWidth(), board.getHeight());
        
        // fill the chess board
	}

	private void renderBoard(int rows, int cols) {

		Insets buttonMargin = new Insets(0,0,0,0);
		int pos = 0;
        for (int ii = 0; ii < rows; ii++) {
            for (int jj = 0; jj < cols; jj++) {
            	Piece p = Game.getInstance().getBoardInstance().getPiece(pos);
            	
                JButton b = new JButton();
                b.setFont(font);
                b.setMargin(buttonMargin);

                String symbol =  (p != null) ? p.getSymbol() : "";
                b.setName(symbol);
                if(symbol != "") {
                	b.setAction(new GameAction(symbol, "pickPiece", pos));
                }
                pos++;
          	
                if ((jj % 2 == 1 && ii % 2 == 1)
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                this.add(b);
            }
        }
	}

    class GameAction extends AbstractAction {

		private static final long serialVersionUID = 1L;
		private int position;
		private String setActionCommand;

		// This is our sample action. It must have an actionPerformed() method,
        // which is called when the action should be invoked.
        public GameAction(String title, String command,int position) {
        	super(title);
        	this.position = position;
        	putValue(ACTION_COMMAND_KEY, command);
        	this.setActionCommand = command;
        }
        
        private void rerenderBoard(Component component) {
    		Component[] listComponents = component.getParent().getComponents();
    		int pos = 0;
    		for (Component comp : listComponents) {
    			int x = (pos % Game.getInstance().getBoardInstance().getWidth());
    			int y = (pos / Game.getInstance().getBoardInstance().getHeight());
    			 if((y % 2 == 1 && x % 2 == 1) ||
    					 (y % 2 == 0 && x % 2 == 0)){
    				 comp.setBackground(Color.WHITE);
    			 } else {
    				 comp.setBackground(Color.BLACK);
    			 }
    			 String symbol = "";
    			 if(board.getPiece(pos) != null){
    				 symbol = board.getPiece(pos).getSymbol();
    			 }
                 ((AbstractButton) comp).setAction(new GameAction(symbol, setActionCommand, pos));
    			pos++;
    		}
        }

        public void actionPerformed(ActionEvent e) {
        	Component currentSource = ((Component) e.getSource());
        	Piece p = Game.getInstance().getBoardInstance().getPiece(position);

        	if(p == null) {
        		return;
        	}
        	
            ChessEvent event = null;
            switch (e.getActionCommand()) {
	            case "pickPiece":
	            	event = new PieceMovesEvent(position, p, currentSource);
	            	setActionCommand = "movePiece";
	            	prevPosition = position;
	            	rerenderBoard(currentSource);
	    			ChessEventDispatcher.getInstance().fireEvent(event);
	                break;
	            case "movePiece":
	            	event = new PieceMovedEvent(prevPosition, position, p);
	            	setActionCommand = "pickPiece";  
	    			ChessEventDispatcher.getInstance().fireEvent(event);
	            	rerenderBoard(currentSource);
	            	Game.getInstance().swapPlayer();
	            	break;
	        }
        }
    }
}
