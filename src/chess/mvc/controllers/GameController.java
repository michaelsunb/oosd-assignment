package chess.mvc.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import chess.core.Board;
import chess.core.Game;
import chess.core.IBoard;
import chess.core.Piece;
import chess.mvc.models.GameNewEvent;
import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.PieceSelectedEvent;
import chess.mvc.models.PieceSplitEvent;
import chess.mvc.models.UpdateUIEvent;
import chess.mvc.views.MainFrame;
import chess.prototype.commands.CommandBase;
import chess.prototype.commands.NewGameCommand;
import chess.prototype.commands.PieceCapturedCommand;
import chess.prototype.commands.PieceJoinCommand;
import chess.prototype.commands.PieceMovedCommand;
import chess.prototype.commands.PieceSelectedCommand;
import chess.prototype.composite.CombinePiece;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;
import chess.prototype.observer.IObserver;

public class GameController extends AbstractAction {
	private static final ChessEventDispatcher eventMgr = ChessEventDispatcher
			.getInstance();
	private static final Game game = Game.getInstance();

	private MainFrame view;

	public GameController() {
	}

	public IBoard getBoard() {
		return game.getBoardInstance();
	}

	public void init(MainFrame view) {
		this.view = view;
		this.view.setVisible(true);
	}

	public IObserver newGame() {
		return new NewGameCommand();
	}

	public IObserver pieceSelected() {
		return new PieceSelectedCommand();
	}

	public IObserver pieceMoved() {
		return  new PieceMovedCommand();
	}

	public IObserver pieceCapture() {
		return new PieceCapturedCommand();
	}

	public IObserver pieceJoin() {
		return new PieceJoinCommand();
	}

	public MainFrame getView() {
		return this.view;
	}
	
	// Actions
	public class NewGameAction extends ActionController {
		public NewGameAction() {
			super(eventMgr);
			event = new GameNewEvent(view);
		}
	}

	public class SaveGameAction extends ActionController {
		public SaveGameAction() {
			super(eventMgr);
			// event = new SaveGameEvent(board?);
		}
	}

	private class PopUpDemo extends JPopupMenu {
		JMenuItem anItem;

		public PopUpDemo() {
			anItem = new JMenuItem("Click Me!");
			add(anItem);
		}
	}

	public class LoadGameAction extends ActionController {
		public LoadGameAction() {
			super(eventMgr);
			// event = new LoadGameEvent(board?);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "PieceSelectedEvent":
			
			eventMgr.fireEvent(new GameNewEvent(view));
			break;

		default:
			//this.view.setTitle(e.getActionCommand());
		}
	}
//	public class PieceAction extends MouseAdapter {
//		private final IBoard board = game.getBoardInstance();
//		private Piece splitPiece;
//		private int position;
//		private int prevPosition;
//		private int nextPosition;
//		private ChessEvent event;
//
//		public PieceAction(int position) {
//			this.position = position;
//		}
//
//		public PieceAction(int position, Piece splitPiece) {
//			this.position = position;
//			this.splitPiece = splitPiece;
//		}
//
//		public void mousePressed(MouseEvent e) {
//			if (SwingUtilities.isRightMouseButton(e)) {
//				// my code
//			}
//			prevPosition = position;
//			event = new PieceSelectedEvent(prevPosition, view);
//			System.out.println("mouse pressed");
//			actionPerformed(null);
//		}
//
//		public void mouseReleased(MouseEvent e) {
//			System.out.println("mouse released");
//
//			calcPosition(e);
//			if (SwingUtilities.isRightMouseButton(e)) {
//				if (nextPosition != position) {
//					return;
//				}
//				PopUpDemo menu = new PopUpDemo();
//				menu.show(e.getComponent(), e.getX(), e.getY());
//				return;
//			}
//
//			Piece mainPiece = board.getPiece(prevPosition);
//			if (mainPiece instanceof CombinePiece) {
//				event = new PieceSplitEvent((CombinePiece) mainPiece,
//						splitPiece, nextPosition);
//			} else {
//				event = new PieceMovedEvent(prevPosition, nextPosition);
//				actionPerformed(null);
//				//event = new UpdateUIEvent((CommandBase) pieceMoved());
//				actionPerformed(null);
//				view.getChessBoardPane().redraw(true);
//			}
//		}
//
//		private void calcPosition(MouseEvent e) {
//			int x = (position % board.getWidth());
//			int y = (position / board.getWidth());
//
//			int newY = (y + e.getY() / 100);
//			if ((y + e.getY() % 100) < 0) {
//				newY--;
//			}
//
//			int newX = (x + e.getX() / 100);
//			if ((x + e.getX() % 100) < 0) {
//				newX--;
//			}
//			nextPosition = (newX + (newY * board.getHeight()));
//		}
//
//		private void actionPerformed(ActionEvent e) {
//			eventMgr.fireEvent(event);
//		}
//	}
//

}
