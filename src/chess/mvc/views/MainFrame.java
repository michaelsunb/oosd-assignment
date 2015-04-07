package chess.mvc.views;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

public class MainFrame extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6716444773355403669L;

	public MainFrame()
	{
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		// create chessboard
		ChessboardViewPanel cb = new ChessboardViewPanel();
		contentPane.add(cb, BorderLayout.CENTER);
		
		// create game status view panel
		GameStatusViewPanel statusPane = new GameStatusViewPanel();
		contentPane.add(statusPane, BorderLayout.SOUTH);

		setSize(605, 660);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Simple Chess Game");
		setLocationRelativeTo(null);
	}
}
