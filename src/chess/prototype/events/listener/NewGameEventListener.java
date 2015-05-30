package chess.prototype.events.listener;

<<<<<<< HEAD:src/chess/prototype/events/listener/NewGameEventListener.java
import javax.swing.JOptionPane;

import chess.core.Game;
import chess.mvc.models.*;
import chess.prototype.events.*;
=======
import chess.mvc.models.GameNewEvent;
import chess.mvc.views.GameOptionViewPanel;
import chess.prototype.observer.ChessEvent;
>>>>>>> origin/stabilize-part-2:src/chess/prototype/commands/NewGameCommand.java

public class NewGameEventListener extends EventListenerBase {

	@Override
	public void update(ChessEvent event) {
		if (!(event instanceof GameNewEvent)) return;
		GameNewEvent theEvent = (GameNewEvent)event;

		new GameOptionViewPanel(theEvent.getMainFame()).setVisible(true);

		theEvent.getMainFame().getChessBoardPane().redraw(true);
		theEvent.getMainFame().revalidate();
	}

}
