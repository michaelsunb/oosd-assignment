package chess.mvc.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import chess.core.Game;
import chess.mvc.controllers.GameController;
import chess.mvc.models.GameNewEvent;
import chess.mvc.models.UndoEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.momento.GameCaretaker;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;

public class MainFrame extends JFrame implements IObserver {
	private final ChessEventDispatcher eventManager;
	
	private ChessboardViewPanel chessboardPane;
	private GameStatusViewPanel statusPane;
	private PieceViewPanel pieceViewPane;
	
	private GameController actionHandler;
	
	public MainFrame(GameController handler) {
		this.eventManager = ChessEventDispatcher.getInstance();

		this.actionHandler = handler;
		this.actionHandler.init(this);
		int width = 870;
		int height = 600;
		
		JPanel contentPane = (JPanel)getContentPane();
		
		// adding Chessboard
		this.chessboardPane = new ChessboardViewPanel(handler);
		contentPane.add(this.chessboardPane, BorderLayout.CENTER);
		
		// build menu
		buildMenuBar();
		
		// add game status
		this.statusPane = new GameStatusViewPanel();
		this.pieceViewPane = new PieceViewPanel(handler);

		JPanel gameInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		contentPane.add(gameInfo, BorderLayout.EAST);
		gameInfo.add(this.statusPane); 
		gameInfo.add(this.pieceViewPane);
		gameInfo.setPreferredSize(new Dimension( 200, 320 ));
		
		setSize(width, height);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Simple Chess Game");
		setLocationRelativeTo(null);
	}
	
	
	private void buildMenuBar() {
		final JMenuBar menuBar = new JMenuBar();
		final Game g = Game.getInstance();
		
		JMenu menu = new JMenu("Game");
		// new game
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new EventAction(new GameNewEvent(actionHandler.getView())));
		menu.add(newGame);
		
		menu.addSeparator();
		
		// save
		JMenuItem saveGame = new JMenuItem("Save");
		saveGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.save();
			}
		});

		saveGame.setEnabled((this.chessboardPane.getSquareSize() > 0));
		menu.add(saveGame);
		
		// restore
		JMenuItem restoreGame = new JMenuItem("Restore");
		restoreGame.setEnabled(new File("game.state").exists());
		restoreGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				g.restore();
				eventManager.fireEvent(new UpdateUIEvent(true));
			}
		});
		menu.add(restoreGame);
		
		menu.addSeparator();
		
		// Undo
		JMenuItem undoGame = new JMenu("Undo");
		JMenuItem undoOne = new JMenuItem("1");
		JMenuItem undoTwo = new JMenuItem("2");
		JMenuItem undoThree = new JMenuItem("3");
		undoGame.add(undoOne);
		undoGame.add(undoTwo);
		undoGame.add(undoThree);

		GameCaretaker caretaker = Game.getInstance().getCaretaker();

		undoGame.setEnabled((caretaker.count() > 0));
		undoOne.setEnabled((caretaker.count() > 0));
		undoTwo.setEnabled((caretaker.count() > 1));
		undoThree.setEnabled((caretaker.count() > 2));
	
		undoOne.addActionListener(new EventAction(new UndoEvent(1)));
		undoTwo.addActionListener(new EventAction(new UndoEvent(2)));
		undoThree.addActionListener(new EventAction(new UndoEvent(3)));
	
		menu.add(undoGame);
		
		menu.addSeparator();
		// exit
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(exit);
		
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	public ChessboardViewPanel getChessBoardPane() {
		return this.chessboardPane;
	}
	
	public GameStatusViewPanel getStatusPane() {
		return this.statusPane;
	}
	
	public PieceViewPanel getPieceViewPane() {
		return this.pieceViewPane;
	}

	@Override
	public void update(ChessEvent event) {
		buildMenuBar();
	}

	public class EventAction extends AbstractAction {
		private ChessEvent event;
		public EventAction(ChessEvent event) {
			this.event = event;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			eventManager.fireEvent(event);
		}
	}
}
