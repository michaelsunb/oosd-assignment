package chess.mvc.controllers;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import chess.core.*;
import chess.mvc.views.*;
import chess.prototype.observer.*;

public class GameController implements IObserver {

	private Container contentPane;
	private JMenuBar menuBar;
	
	private void createBoard() {
        contentPane.removeAll();
		contentPane.add(menuBar, BorderLayout.NORTH);
		
		// create chessboard
		ChessboardViewPanel cb = new ChessboardViewPanel();
		contentPane.add(cb, BorderLayout.CENTER);
		
		// create game status view panel
		GameStatusViewPanel statusPane = new GameStatusViewPanel();
		contentPane.add(statusPane, BorderLayout.SOUTH);
		contentPane.revalidate();
	}
	
	@Override
	public void update(ChessEvent event) {
		if (event instanceof NewGameEvent)
		{
			menuBar = ((NewGameEvent) event).getMenuBar();
			contentPane = ((NewGameEvent) event).getContainer();
	    	try {
	        	String moves = JOptionPane.showInputDialog(contentPane,
	                    "How many moves?",
	                    "alert", 
	                    JOptionPane.OK_CANCEL_OPTION);
	        	if(moves == null) { // cancelled
	        		return;
	        	}
	        	Game.getInstance().reset(Integer.parseInt(moves));
	    	} catch(NumberFormatException nfe) {
	    		JOptionPane.showMessageDialog(null, "Not a number!");
	    	}
			createBoard();
		}
	}
}
