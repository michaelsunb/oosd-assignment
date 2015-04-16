package chess.mvc.views;

import java.awt.Color;

import javax.swing.*;

import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.GameStatusEvent;
import chess.prototype.observer.IObserver;
import chess.prototype.observer.PieceCapturedEvent;

/*
 * A panel which render game status
 */
public class GameStatusViewPanel extends JPanel implements IObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6869843804453967033L;

	private int numberOfMoves = 0;
	private int score = 0;

	public GameStatusViewPanel() {
		/*
		 * Creating label for displaying player 1 info
		 */

		createStatus(1);

		JLabel playerDivedLabel = new JLabel("\t|\t");
		this.add(playerDivedLabel);
		
		createStatus(2);
	}
	
	private void createStatus(int player) {
		JLabel playerLabel = new JLabel("Player " + player);
		this.add(playerLabel);
		JLabel playerMovedLabel = new JLabel("Moves " + numberOfMoves);
		this.add(playerMovedLabel);
		JLabel playerScoredLabel = new JLabel("Scores " + score);
		this.add(playerScoredLabel);
		this.revalidate();
	}

	@Override
	public void update(ChessEvent event) {
		if(event instanceof GameStatusEvent) {
			numberOfMoves = ((GameStatusEvent)event).getNumberOfMoves() - numberOfMoves;
			score += ((GameStatusEvent)event).getTargetPiece().getScore();
			if(((GameStatusEvent)event).getTargetPiece().getColour() == Color.WHITE) {
				createStatus(2);
			} else {
				createStatus(1);
			}
			//System.out.println(numberOfMoves);
		}
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
