package chess.mvc.views;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import chess.core.Game;
import chess.prototype.decorator.GameMapDecorator;

/*
 * A Pop-up screen asking user for input
 * Base on the given input a new game start
 * 1) render board
 */
public class GameOptionViewPanel extends JDialog {
	private JTextField txtMaxMove;
	private JTextArea txtMap;

	/**
	 * @pre.condition: Instantiate the object with JFrame input
	 * @post.condition: Creates a popup screen to start the game
	 */
	public GameOptionViewPanel(JFrame frame) {
		
		super(frame, "Game Options", JDialog.ModalityType.DOCUMENT_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		// Max move
		panel.add(new JLabel("Max Move"));
		this.txtMaxMove = new JTextField("10");
		panel.add(txtMaxMove);

		// advance mapping
		panel.add(new JLabel("Custom Chessboard"));
		this.txtMap = new JTextArea(4, 20);
		this.txtMap.setText("RBKJMKBR\nSSSSSSSS\nSSSSSSSS\nXXXXXXXX");
		panel.add(this.txtMap);
		
		this.add(panel, BorderLayout.CENTER);
		
		// basic game
		JButton btnBasic = new JButton("Basic");
		btnBasic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().reset(10);
				dispose();
			}
		});
		
		// advance button
		JButton btnAdvance = new JButton("Advance");
		btnAdvance.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// maxMove
				int maxMove = 0;
				try {
					maxMove = Integer.parseInt(txtMaxMove.getText());
				} catch (NumberFormatException e1) {
					maxMove = 10;
				}
				
				String lines[] = txtMap.getText().split("\\n");
				ArrayList<String> map = new ArrayList<>(Arrays.asList(lines));

				// here how the actual game get setup
				if (GameMapDecorator.gameSquareMapValidity(map)) {					
					Game.getInstance().reset(maxMove, false);
					new GameMapDecorator(Game.getInstance().getBoardInstance(),
							map).init();
					
				} else {
					Game.getInstance().reset(maxMove);
				}

				dispose();
			}
		});
		
		// button
		JPanel cmdPane = new JPanel();
		cmdPane.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		cmdPane.add(btnBasic);
		cmdPane.add(btnAdvance);
		
		this.add(cmdPane, BorderLayout.SOUTH);
		
		this.pack();
		this.setResizable(false);
	}

}
