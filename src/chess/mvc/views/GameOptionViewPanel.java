package chess.mvc.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * A Pop-up screen asking user for input
 * Base on the given input a new game start
 * 1) render board
 */
public class GameOptionViewPanel extends JPanel {
	private JTextField txtMaxMove;
	private JTextArea txtMap;
	private JTextField txtTimeout;
	
	public GameOptionViewPanel() {
		// Max move
		this.add(new JLabel("Max Move"));
		this.txtMaxMove = new JTextField("10");
		this.add(txtMaxMove);
		
		// advance mapping
		this.add(new JLabel("Custom Chessboard"));
		this.txtMap = new JTextArea(4,20);
		this.add(this.txtMap);
		
		// Timeout
		this.add(new JLabel("Timeout"));
		this.txtTimeout = new JTextField();
		this.add(this.txtTimeout);
		
		// start button
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO validate data
			}
			
		});
	}
	
	
}
