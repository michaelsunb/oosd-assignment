package chess.tests;

import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JButton;

import org.junit.*;

import chess.core.*;
import chess.mvc.controllers.GameController;
import chess.mvc.models.PieceSelectedEvent;
import chess.mvc.views.ChessboardViewPanel;
import chess.mvc.views.MainFrame;
import chess.prototype.commands.*;

public class PieceSelectedTest extends GameTestBase {

	@Before
	public void setUp() throws Exception {
		this.eventMgr().removeAll();
		this.eventMgr().addListener("PieceSelectedEvent",
				new PieceSelectedCommand());

		Game.getInstance().reset(10);
	}

	@Test
	public void rook_selected() {
		// arrange
		GameController controller = new GameController();
		MainFrame view = new MainFrame(controller);
		view.getChessBoardPane().redraw(true);

		PieceSelectedEvent event = new PieceSelectedEvent(0, view);

		// act
		this.eventMgr().fireEvent(event);

		// assert
		ChessboardViewPanel chessBoard = view.getChessBoardPane();
		int[] positions = this.getBoard().getPiece(0).getMovablePositions(0);
		
		for (int i : positions) {
			assertEquals("Movable square should has red background.",
					Color.RED,
					chessBoard.getSquare(i).getBackground());
		}
		
		assertNotNull("Selected Piece is not null", this.getGame().getSelectedPiece());
	}
	
	@Test
	public void player_select_enemy_piece_ignore_path() {
		// arrange
		GameController controller = new GameController();
		MainFrame view = new MainFrame(controller);
		view.getChessBoardPane().redraw(true);
		int pos = 30;
		PieceSelectedEvent event = new PieceSelectedEvent(pos, view);

		// act
		this.eventMgr().fireEvent(event);

		// assert
		ChessboardViewPanel chessBoard = view.getChessBoardPane();
		int[] positions = this.getBoard().getPiece(pos).getMovablePositions(pos);
		
		for (int i : positions) {
			assertNotSame("Player select on enemy piece should not see movable positions",
					Color.RED,
					chessBoard.getSquare(i).getBackground());
		}
		
		assertNull("Selected Piece is null", this.getGame().getSelectedPiece());
	}
	
	@Test
	public void player_select_barrier_piece_ignore_path() {
		// arrange
		GameController controller = new GameController();
		MainFrame view = new MainFrame(controller);
		view.getChessBoardPane().redraw(true);
		int pos = 12;
		PieceSelectedEvent event = new PieceSelectedEvent(pos, view);

		// act
		this.eventMgr().fireEvent(event);

		// assert
		ChessboardViewPanel chessBoard = view.getChessBoardPane();
		int[] positions = this.getBoard().getPiece(pos).getMovablePositions(pos);
		
		assertTrue("Barrier can't move", positions.length == 0);
		
		assertNull("Selected Piece is null", this.getGame().getSelectedPiece());
	}
	
	@Test
	public void selected_event_ignore_if_game_is_over()
	{
		// arrange
		Game.getInstance().reset(0);
		GameController controller = new GameController();
		MainFrame view = new MainFrame(controller);
		view.getChessBoardPane().redraw(true);
		int pos = 0;
		PieceSelectedEvent event = new PieceSelectedEvent(0, view);

		// act
		this.eventMgr().fireEvent(event);

		// assert
		ChessboardViewPanel chessBoard = view.getChessBoardPane();
		int[] positions = this.getBoard().getPiece(pos).getMovablePositions(pos);
		for (int i : positions) {
			assertNotSame("Game is over: no path shall be marked.",
					Color.RED,
					chessBoard.getSquare(i).getBackground());
		}
	}
}
