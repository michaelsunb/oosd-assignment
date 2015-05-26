package chess.prototype.commands;

import javax.swing.JOptionPane;

import chess.core.Game;
import chess.mvc.models.*;
import chess.mvc.views.GameOptionViewPanel;
import chess.prototype.observer.*;

public class NewGameCommand extends CommandBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof GameNewEvent)) return;
		GameNewEvent theEvent = (GameNewEvent)event;

		new GameOptionViewPanel(theEvent.getMainFame()).setVisible(true);

		theEvent.getMainFame().getChessBoardPane().redraw(true);
		theEvent.getMainFame().revalidate();
		
//		try {
//			String moves = JOptionPane.showInputDialog(
//					gameNewEvent.getMainFame(),
//					"How many moves?", "alert", JOptionPane.OK_CANCEL_OPTION);
//
//			if (moves == null) { // cancelled
//				return;
//			}
//			
//			Game.getInstance().reset(Integer.parseInt(moves));
//			
//			theEvent.getMainFame().getChessBoardPane().redraw(true);
//			theEvent.getMainFame().revalidate();
//			
//		} catch (NumberFormatException nfe) {
//			JOptionPane.showMessageDialog(null, "Not a number!");
//		}
		
	}

}
