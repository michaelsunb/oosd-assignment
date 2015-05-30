package chess.mvc.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import chess.core.Game;
import chess.mvc.controllers.GameController;
import chess.prototype.momento.GameCaretaker;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.IObserver;
import chess.mvc.models.UpdateUIEvent;
import chess.prototype.observer.ChessEventDispatcher;

public class MainFrame extends JFrame implements IObserver {
	private static final long serialVersionUID = 1L;
	private ChessboardViewPanel chessboardPane;
	private GameStatusViewPanel statusPane;
	private PieceViewPanel pieceViewPane;
	
	private GameController actionHandler;
	
	public MainFrame(GameController handler) {
		this.actionHandler = handler;
		this.actionHandler.init(this);
		int width = 870;
		int height = 600;
		
		JPanel contentPane = (JPanel)getContentPane();
		
		// build menu
		buildMenuBar();
		
		// adding Chessboard
		this.chessboardPane = new ChessboardViewPanel(handler);
		contentPane.add(this.chessboardPane, BorderLayout.CENTER);
		
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
		JMenu menu = new JMenu("Game");
		// new game
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(this.actionHandler.new NewGameAction());	
		menu.add(newGame);
		
		menu.addSeparator();
		
		// save
		JMenuItem saveGame = new JMenuItem("Save");
		saveGame.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().save();
			}
		});
		menu.add(saveGame);
		
		// restore
		JMenuItem restoreGame = new JMenuItem("Restore");
		restoreGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().restore();
				ChessEventDispatcher.getInstance().fireEvent(new UpdateUIEvent());
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

		undoGame.setEnabled(false);
		undoOne.setEnabled(false);
		undoTwo.setEnabled(false);
		undoThree.setEnabled(false);
		if(caretaker.count() > 0) undoGame.setEnabled(true);
		if(caretaker.count() > 0) undoOne.setEnabled(true);
		if(caretaker.count() > 1) undoTwo.setEnabled(true);
		if(caretaker.count() > 2) undoThree.setEnabled(true);
		undoOne.addActionListener(this.actionHandler.new UndoGameAction(1));
		undoTwo.addActionListener(this.actionHandler.new UndoGameAction(2));
		undoThree.addActionListener(this.actionHandler.new UndoGameAction(3));
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
}
