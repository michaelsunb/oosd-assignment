package chess.mvc.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
	private JLabel[] players;
	private JLabel[] moves; 
	private JLabel[] scores;
	private JLabel timeCounter;
	private static final int PLAYER_ONE = 0;
	private static final int PLAYER_TWO = 1;
	
	public GameStatusViewPanel() {
		JPanel statusPane = playerStats();
		this.add(statusPane);
	}


	private JPanel playerStats() {		
		JPanel statusPane = new JPanel();
		statusPane.setLayout(new GridLayout (3, 3));

		// score & move
		players = new JLabel[] {
				new JLabel("ONE"),
				new JLabel("TWO")
		};
		moves = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		scores = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		
		JLabel lbPlayer = new JLabel("Player ");
		int lbHeight = lbPlayer.getPreferredSize().height;
		int lbWidth =  60;
		
		lbPlayer.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbPlayer);
		statusPane.add(players[0]);
		statusPane.add(players[1]);
		
		JLabel lbMove = new JLabel("Move ");
		lbMove.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbMove);
		statusPane.add(moves[0]);
		statusPane.add(moves[1]);
		
		JLabel lbScore = new JLabel("Score "); 
		lbScore.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbScore);
		scores[0].setPreferredSize(new Dimension(20, lbHeight));
		statusPane.add(scores[0]);
		statusPane.add(scores[1]);
		
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
		
		players[0].setForeground(g.getPlayer(1).isTurn() ? Color.BLUE : Color.BLACK);
		players[1].setForeground(g.getPlayer(2).isTurn() ? Color.BLUE : Color.BLACK);
	}
}
