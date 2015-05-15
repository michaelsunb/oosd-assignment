package chess.mvc.views;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		JPanel statusPane = playerStats();
		this.add(statusPane);

		this.revalidate();
		this.setBounds(new Rectangle(60, 60));
	}


	private JPanel playerStats() {
		JPanel statusPane = new JPanel();
		statusPane.setLayout(new GridBagLayout ());

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		
		c.anchor = GridBagConstraints.LINE_START;
		// player one
		statusPane.add(new JLabel("Player One"), c);
		c.anchor = GridBagConstraints.LINE_END;
		c.gridy++;
		statusPane.add(new JLabel("Move: "), c);
		
		c.gridy++;
		statusPane.add(new JLabel("Score: "), c);

		// player two
		c.gridy++;
		statusPane.add(new JLabel("Player Two"), c);
		c.anchor = GridBagConstraints.LINE_END;
		c.gridy++;
		statusPane.add(new JLabel("Move: "), c);
		
		c.gridy++;
		statusPane.add(new JLabel("Score: "), c);
		
		// score & move
		moves = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		scores = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		
		c.gridx = 1;
		c.gridy = 1;
		statusPane.add(moves[0], c);
		c.gridy++;
		statusPane.add(scores[0], c);

		c.gridy += 2;
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
