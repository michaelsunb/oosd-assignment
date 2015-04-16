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
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.NewGameEvent;
import chess.prototype.observer.PieceMovesEvent;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private Container contentPane;
	private JMenuBar menuBar;
	private static final long serialVersionUID = -6716444773355403669L;

	public MainFrame()
	{
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		menuBar();

		setSize(605, 660);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Simple Chess Game");
		setLocationRelativeTo(null);
	}
	
	private void menuBar() {
		menuBar = new JMenuBar();

        JMenu menu = new JMenu("Menu");

        GameAction exampleAction = new GameAction("New Game","NewGameEvent");

        menu.add(exampleAction);
        menuBar.add(menu);
        
        contentPane.add(menuBar, BorderLayout.NORTH);
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
	            case "NewGameEvent":
	            	ChessEvent event = new NewGameEvent(contentPane, menuBar);
	            	ChessEventDispatcher.getInstance().fireEvent(event);
	            	break;
	        }
        }
    }
}
