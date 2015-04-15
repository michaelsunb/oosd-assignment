package chess.mvc.views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import chess.core.Game;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	Container contentPane;
	private static final long serialVersionUID = -6716444773355403669L;
	
	Game gameModel;

	public MainFrame(Game game)
	{
		gameModel = game;
		
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		menuBar();
		createBoard();

		setSize(605, 660);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Simple Chess Game");
		setLocationRelativeTo(null);
	}
	
	private void menuBar() {
		JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");

        GameAction exampleAction = new GameAction("New Game","New");

        menu.add(exampleAction);
        menuBar.add(menu);
        
        contentPane.add(menuBar, BorderLayout.NORTH);
	}
	
	private void createBoard() {
		// create chessboard
		ChessboardViewPanel cb = new ChessboardViewPanel();
		contentPane.add(cb, BorderLayout.CENTER);
		contentPane.revalidate();
		
		// create game status view panel
		GameStatusViewPanel statusPane = new GameStatusViewPanel();
		contentPane.add(statusPane, BorderLayout.SOUTH);
	}

    class GameAction extends AbstractAction {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		// This is our sample action. It must have an actionPerformed() method,
        // which is called when the action should be invoked.
		
        public GameAction(String title, String command) {
        	super(title);
        	putValue(ACTION_COMMAND_KEY, command);
        }

        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
	            case "New":
	            	try {
		            	String moves = JOptionPane.showInputDialog((JComponent) e.getSource(),
		                        "How many moves?",
		                        "alert", 
		                        JOptionPane.OK_CANCEL_OPTION);
		            	if(moves == null) { // cancelled
		            		return;
		            	}
		            	gameModel.reset(Integer.parseInt(moves));
			            contentPane.removeAll();
			            menuBar();
			    		createBoard();
	            	} catch(NumberFormatException nfe)   {
	            		JOptionPane.showMessageDialog(null, "Not a number!");
	            	}
	            	break;
	        }
        }
    }
}
