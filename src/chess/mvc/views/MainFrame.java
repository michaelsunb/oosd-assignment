package chess.mvc.views;

import java.awt.*;

import javax.swing.*;

import chess.mvc.controllers.GameController;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ChessboardViewPanel chessboardPane;
	private GameStatusViewPanel statusPane;
	private PieceViewPanel pieceViewPane;
	
	private GameController actionHandler;
	
	public MainFrame(GameController handler) {
		this.actionHandler = handler;
		this.actionHandler.init(this);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		// build menu
		buildMenuBar();
		
		// adding Chessboard
		this.chessboardPane = new ChessboardViewPanel(handler);
		contentPane.add(this.chessboardPane, BorderLayout.CENTER);
		
		// add game status
		this.statusPane = new GameStatusViewPanel();
		this.pieceViewPane = new PieceViewPanel(handler);
		
		JPanel gameInfo = new JPanel();
		gameInfo.setLayout(new GridLayout(0, 1));
		gameInfo.setBounds(new Rectangle(60, contentPane.getHeight()));
		
		gameInfo.add(this.statusPane); 
		gameInfo.add(this.pieceViewPane);
		
		contentPane.add(gameInfo, BorderLayout.EAST);
		
		setSize(870, 660);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("Simple Chess Game");
		setLocationRelativeTo(null);
		
		//contentPane.revalidate();
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
		saveGame.addActionListener(this.actionHandler.new SaveGameAction());
		menu.add(saveGame);
		
		// restore
		JMenuItem restoreGame = new JMenuItem("Restore");
		restoreGame.addActionListener(this.actionHandler.new LoadGameAction());
		menu.add(restoreGame);
		
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}

	public ChessboardViewPanel getChessBoardPane() {
		return this.chessboardPane;
	}
	
	public GameStatusViewPanel getStatusPane() {
		return this.statusPane;
	}
}
