package chess.mvc.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.*;

import chess.core.Game;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.IObserver;

/*
 * A panel which render game status
 */
public class GameStatusViewPanel extends JPanel implements IObserver {
	private JLabel[] moves; 
	private JLabel[] scores;
	private JLabel timeCounter;
	private static final int PLAYER_ONE = 0;
	private static final int PLAYER_TWO = 1;
	
	public GameStatusViewPanel() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0; c.gridy = 0;
		
		JPanel statusPane = playerStats();
		this.add(statusPane, c);

		this.setBounds(new Rectangle(60, 60));
		this.setBackground(Color.YELLOW);
	}


	private JPanel playerStats() {		
		JPanel statusPane = new JPanel();
		statusPane.setLayout(new GridBagLayout ());

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3, 3, 3, 3);
		c.gridx = 0;
		c.gridy = 0;
		
		JLabel lbPlayer = new JLabel("Player: ", SwingConstants.RIGHT);
		int lbHeight = lbPlayer.getPreferredSize().height;
		int lbWidth =  60;
		
		lbPlayer.setPreferredSize(new Dimension(lbWidth, lbHeight));
		c.anchor = GridBagConstraints.LINE_START;
		// player one
		statusPane.add(lbPlayer, c);
		c.gridx = 1;
		statusPane.add(new JLabel("ONE"), c);
		
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 0;
		c.gridy++;
		JLabel lbMove = new JLabel("Move: ", SwingConstants.RIGHT);
		lbMove.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbMove, c);
		
		c.gridy++;
		JLabel lbScore = new JLabel("Score: ", SwingConstants.RIGHT); 
		lbScore.setPreferredSize(new Dimension(lbWidth, lbHeight));
		
		statusPane.add(lbScore, c);

		// player two
		c.gridx = 3;
		c.gridy = 0;
		statusPane.add(new JLabel("TWO"), c);
		c.anchor = GridBagConstraints.LINE_END;
		
		// score & move
		moves = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		scores = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		
		// column 2
		c.gridx = 1;
		c.gridy = 1;
		
		moves[0].setPreferredSize(new Dimension(20, lbHeight));
		statusPane.add(moves[0], c);
		c.gridy++;
		scores[0].setPreferredSize(new Dimension(20, lbHeight));
		statusPane.add(scores[0], c);
		
		c.gridx = 2;
		c.gridy = 1;
		statusPane.add(moves[1], c);
		c.gridy++;
		statusPane.add(scores[1], c);
		return statusPane;
	}


	@Override
	public void update(ChessEvent event) {
		// NOTE in fact we can get info from Game.getInstance();
		// all we need is someone call this method
		Game g = Game.getInstance();
		
		moves[PLAYER_ONE].setText("" + g.getPlayer(1).getNumberOfMove());
		scores[PLAYER_ONE].setText("" + g.getPlayer(1).getScore());
		
		moves[PLAYER_TWO].setText("" + g.getPlayer(2).getNumberOfMove());
		scores[PLAYER_TWO].setText("" + g.getPlayer(2).getScore());
	}
}
