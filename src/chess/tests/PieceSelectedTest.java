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
					((JButton) chessBoard.getSquare(i)).getBackground());
		}
		
		assertNotNull("Selected Piece is not null", this.getGame().getSelectedPiece());
	}

	@Test
	public void select_a_piece_then_right_click_on_diff_pos_equal_move() {
		fail("Not yet implemented");
	}
}
