package chess.mvc.views;

import java.awt.*;

import javax.swing.*;

import chess.core.Game;
import chess.prototype.observer.*;

/*
 * A panel which render game status
 */
public class GameStatusViewPanel extends JPanel implements IObserver {
	// player label
	private JLabel[] lbPlayers;
	// each player move
	private JLabel[] lbMoves; 
	// each player score
	private JLabel[] lbScores;
	// each player available pieces
	private JLabel[] lbPieces;
	// game status
	private JLabel gameStatus;
	
	private static final int PLAYER_ONE = 0;
	private static final int PLAYER_TWO = 1;
	
	public GameStatusViewPanel() {
		// game state
		this.gameStatus = new JLabel("");
		Font font = this.gameStatus.getFont();
		this.gameStatus.setFont(new Font(font.getFontName(), Font.BOLD,  14));
		this.add(this.gameStatus);
		
		JPanel statusPane = playerStats();
		this.add(statusPane);

		this.setPreferredSize(new Dimension(200, 110));
	}

	/**
	 * @pre.condition: New game must have begun
	 * @post.condition: Returns JPanel of initial scores/moves/turns
	 */
	private JPanel playerStats() {		
		JPanel statusPane = new JPanel();
		statusPane.setLayout(new GridLayout (4, 3));

		this.lbPlayers = new JLabel[] {
				new JLabel("ONE"),
				new JLabel("TWO")
		};
		this.lbMoves = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		this.lbScores = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		this.lbPieces = new JLabel[] {
				new JLabel("0"),
				new JLabel("0")
		};
		
		JLabel lbPlayer = new JLabel("Player ");
		int lbHeight = lbPlayer.getPreferredSize().height;
		int lbWidth =  60;
		
		lbPlayer.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbPlayer);
		statusPane.add(this.lbPlayers[0]);
		statusPane.add(this.lbPlayers[1]);
		
		// move statuses
		JLabel lbMove = new JLabel("Move ");
		lbMove.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbMove);
		statusPane.add(this.lbMoves[0]);
		statusPane.add(this.lbMoves[1]);
		
		// score status
		JLabel lbScore = new JLabel("Score "); 
		lbScore.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbScore);
		this.lbScores[0].setPreferredSize(new Dimension(20, lbHeight));
		statusPane.add(this.lbScores[0]);
		statusPane.add(this.lbScores[1]);
		
		// piece count
		JLabel lbPieces = new JLabel("Piece "); 
		lbPieces.setPreferredSize(new Dimension(lbWidth, lbHeight));
		statusPane.add(lbPieces);
		statusPane.add(this.lbPieces[0]);
		statusPane.add(this.lbPieces[1]);
		
		return statusPane;
	}

	/**
	 * @pre.condition: A chess event must be fired
	 * @post.condition: Updates the JPanel of scores/moves/turns
	 */
	@Override
	public void update(ChessEvent event) {
		// NOTE in fact we can get info from Game.getInstance();
		// all we need is someone call this method
		Game g = Game.getInstance();
		this.lbMoves[PLAYER_ONE].setText("" + g.getPlayer(1).getNumberOfMove());
		this.lbMoves[PLAYER_TWO].setText("" + g.getPlayer(2).getNumberOfMove());
		
		this.lbScores[PLAYER_ONE].setText("" + g.getPlayer(1).getScore());
		this.lbScores[PLAYER_TWO].setText("" + g.getPlayer(2).getScore());
		
		this.lbPlayers[0].setForeground(g.getPlayer(1).isTurn() ? Color.BLUE : Color.BLACK);
		this.lbPlayers[1].setForeground(g.getPlayer(2).isTurn() ? Color.BLUE : Color.BLACK);
		
		this.lbPieces[PLAYER_ONE].setText("" + g.getPlayerPieces(PLAYER_ONE + 1).size());
		this.lbPieces[PLAYER_TWO].setText("" + g.getPlayerPieces(PLAYER_TWO + 1).size());

		if (g.isGameOver()) {
			this.gameStatus.setForeground(Color.RED);
			this.gameStatus.setText("GAME OVER!!!");
		} else {
			this.gameStatus.setForeground(Color.BLUE);
			this.gameStatus.setText("GAME PLAY");
		}
	}
}
