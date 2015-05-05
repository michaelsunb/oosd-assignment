package chess.prototype.events.listener;

import javax.swing.JOptionPane;

import chess.core.Game;
import chess.mvc.models.*;
import chess.prototype.events.*;

public class NewGameEventListener extends EventListenerBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof GameNewEvent)) return;
		GameNewEvent gameNewEvent = (GameNewEvent)event;
		
		try {
			String moves = JOptionPane.showInputDialog(
					gameNewEvent.getMainFame(),
					"How many moves?", "alert", JOptionPane.OK_CANCEL_OPTION);

			if (moves == null) { // cancelled
				return;
			}
			
			Game.getInstance().reset(Integer.parseInt(moves));
			gameNewEvent.getMainFame().getChessBoardPane().redraw(true);
			gameNewEvent.getMainFame().revalidate();

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Not a number!");
		}
		
	}

}
