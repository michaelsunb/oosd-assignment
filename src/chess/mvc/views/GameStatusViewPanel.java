package chess.mvc.views;

import java.awt.*;

import javax.swing.*;

import chess.core.Game;
import chess.prototype.observer.*;

/*
 * A panel which render game status
 */
public class GameStatusViewPanel extends JPanel implements IObserver {
	private JLabel[] players;
	private JLabel[] moves; 
	private JLabel[] scores;
	private JLabel gameState;
	private JLabel timeCounter;
	private static final int PLAYER_ONE = 0;
	private static final int PLAYER_TWO = 1;
	
	public GameStatusViewPanel() {
		// game state
		this.gameState = new JLabel("");
		Font font = this.gameState.getFont();
		this.gameState.setFont(new Font(font.getFontName(), Font.BOLD,  14));
		this.add(this.gameState);
		
		JPanel statusPane = playerStats();
		this.add(statusPane);

		this.setPreferredSize(new Dimension(200, 90));
	}


	private JPanel playerStats() {		
		JPanel statusPane = new JPanel();
		statusPane.setLayout(new GridLayout (3, 3));

		// score & move
		this.players = new JLabel[] {
				new JLabel("ONE"),
				new JLabel("TWO")
		};
		this.moves = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		this.scores = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		
		JLabel lbPlayer = new JLabel("Player ");
		int lbHeight = lbPlayer.getPreferredSize().height;
		int lbWidth =  60;
		
		lbPlayer.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbPlayer);
		statusPane.add(this.players[0]);
		statusPane.add(this.players[1]);
		
		JLabel lbMove = new JLabel("Move ");
		lbMove.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbMove);
		statusPane.add(this.moves[0]);
		statusPane.add(this.moves[1]);
		
		JLabel lbScore = new JLabel("Score "); 
		lbScore.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbScore);
		this.scores[0].setPreferredSize(new Dimension(20, lbHeight));
		statusPane.add(this.scores[0]);
		statusPane.add(this.scores[1]);
		
		return statusPane;
	}


	@Override
	public void update(ChessEvent event) {
		// NOTE in fact we can get info from Game.getInstance();
		// all we need is someone call this method
		Game g = Game.getInstance();
		
		this.moves[PLAYER_ONE].setText("" + g.getPlayer(1).getNumberOfMove());
		this.scores[PLAYER_ONE].setText("" + g.getPlayer(1).getScore());
		
		this.moves[PLAYER_TWO].setText("" + g.getPlayer(2).getNumberOfMove());
		this.scores[PLAYER_TWO].setText("" + g.getPlayer(2).getScore());
		
		this.players[0].setForeground(g.getPlayer(1).isTurn() ? Color.BLUE : Color.BLACK);
		this.players[1].setForeground(g.getPlayer(2).isTurn() ? Color.BLUE : Color.BLACK);
		

		if (g.isGameOver()) {
			this.gameState.setForeground(Color.RED);
			this.gameState.setText("GAME OVER!!!");
		} else {
			this.gameState.setForeground(Color.BLUE);
			this.gameState.setText("GAME PLAY");
		}
	}
}
