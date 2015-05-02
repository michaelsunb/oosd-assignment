package chess.mvc.views;

import java.awt.*;

import javax.swing.*;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ChessboardViewPanel chessboardPane;
	private GameStatusViewPanel statusPane;
	private PieceViewPanel pieceViewPane;
	
	private AbstractAction actionHandler;
	
	public MainFrame(AbstractAction handler) {
		this.actionHandler = handler;
		
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
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setActionCommand("NewGameEvent");
		
		newGame.addActionListener(this.actionHandler);
		
		menu.add(newGame);
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
