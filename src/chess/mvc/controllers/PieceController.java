package chess.mvc.controllers;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import chess.core.Game;
import chess.core.Piece;

public class PieceController implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JComponent currentSource = ((JComponent) e.getSource());
		int position = Integer.parseInt(e.getActionCommand());

		// TODO put game controller
		Game game = Game.getInstance();
		game.reset(100);
		
		Piece piece = game.getBoardInstance().getPiece(position);
		if(piece != null) {
			int movablePos[] = piece.getMovablePositions(position);

			for(int square : movablePos) {
				Component movableSource = currentSource.getParent().getComponent(square);
				movableSource.setBackground(Color.RED);
			}
		}
	}
}
