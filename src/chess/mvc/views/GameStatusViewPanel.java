package chess.mvc.views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.*;

import chess.mvc.models.GameStatusEvent;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.IObserver;

/*
 * A panel which render game status
 */
public class GameStatusViewPanel extends JPanel implements IObserver {
	private int numberOfMoves = 0;
	private int score = 0;

	public GameStatusViewPanel() {
		this.setLayout(new GridLayout (0, 1));

		createStatus(1);
		createStatus(2);
		
		this.setBounds(new Rectangle(60, 60));
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
		if (event instanceof GameStatusEvent) {
			updateGameStatus((GameStatusEvent) event);
		}
	}

	private void updateGameStatus(GameStatusEvent event) {
		numberOfMoves = event.getNumberOfMoves() - numberOfMoves;
		score += event.getTargetPiece().getScore();

		if (event.getTargetPiece().getColour() == Color.WHITE) {
			createStatus(2);
		} else {
			createStatus(1);
		}
	}
}
