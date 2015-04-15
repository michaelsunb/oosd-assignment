package chess.mvc.views;

import javax.swing.*;

import chess.core.Player;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.IObserver;
import chess.prototype.observer.PieceCapturedEvent;
import chess.prototype.observer.PieceMovedEvent;

/*
 * A panel which render game status
 */
public class GameStatusViewPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6869843804453967033L;
	private static JLabel player1Label;
	private static JLabel player1NumMovedLabel;
	private static JLabel player1ScoredLabel;
	
	private static JLabel player2Label;
	private static JLabel player2NumMovedLabel;
	private static JLabel player2ScoredLabel;
	
	public GameStatusViewPanel() {
		
		/*
		 * Creating label for displaying player 1 info
		 */
		player1Label = new JLabel("Player 1");
		player1NumMovedLabel = new JLabel("Moved");
		player1ScoredLabel = new JLabel("Scored");
		
		this.add(player1Label);
		this.add(player1NumMovedLabel);
		this.add(player1ScoredLabel);
		
		/*
		 * Creating label for displaying player 2 info
		 */
		player2Label = new JLabel("Player 2");
		player2NumMovedLabel = new JLabel("Moved");
		player2ScoredLabel = new JLabel("Scored");
		
		this.add(player2Label);
		this.add(player2NumMovedLabel);
		this.add(player2ScoredLabel);
		
	}

	/*
	 * TODO: DBC enforce that players.length == 2
	 */
//	public void update(Player[] players) {
//		/*
//		 * Update player 1
//		 */
//		player1NumMovedLabel.setText( String.format("Moved: {0}", players[0].getNumberOfMove()) );
//		player1NumMovedLabel.setText( String.format("Score: {0}", players[0].getScore()) );
//		
//		/*
//		 * Update player 2
//		 */
//		player2NumMovedLabel.setText( String.format("Moved: {0}", players[1].getNumberOfMove()) );
//		player2NumMovedLabel.setText( String.format("Score: {0}", players[1].getScore()) );
//	}
	
}
