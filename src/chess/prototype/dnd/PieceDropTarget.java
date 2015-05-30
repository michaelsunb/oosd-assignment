/*
 * Author: Sokun, CHORN
 * Number: s3455783
 */
package chess.prototype.dnd;

import java.awt.datatransfer.*;
import java.awt.dnd.*;

import chess.core.*;
import chess.mvc.models.*;
import chess.mvc.views.*;
import chess.prototype.composite.*;
import chess.prototype.observer.*;

public class PieceDropTarget extends DropTargetAdapter implements
		DropTargetListener {
	private DropTarget target;
	private Square square;
	
	public PieceDropTarget(Square square) {
		this.square = square;
		
		target = new DropTarget(square, DnDConstants.ACTION_COPY, this, true, null);
	}
	
	@Override
	public void drop(DropTargetDropEvent event) {
		try {
			Transferable tr = event.getTransferable();
			DataFlavor flavor = TransferablePiece.flavors[0];
			Piece piece = (Piece) tr.getTransferData(flavor);
			Game game = Game.getInstance();
			
			int prevPosition = game.getSelPosition();
			int tagetPosition = this.square.getPosition();
			Piece prevPiece = game.getBoardInstance().getPiece(prevPosition);
			
			if (!event.isDataFlavorSupported(flavor)
					|| prevPiece == null) {
				event.rejectDrop();
				return;
			}
			
			if (!piece.canMoveTo(prevPosition, tagetPosition)) {
				System.out.println("Nah! you can't move there.");
				event.rejectDrop();
				return;
			}
			// traverse UX tree, it would be much much MUCH better if we can 
			// get a inject the MainFrame reference directly. However, just to proof
			// how awesome Java UI stack is :)
			MainFrame mf = (MainFrame)this.square
					.getParent()	// ChessboardViewPanel
					.getParent()	// JPanel
					.getParent()	// JLayerPanel
					.getParent()	// JRootPane
					.getParent();	// MainFrame :) 
			// get selected view
			PieceViewPanel pvp = mf.getPieceViewPane();
			
			ChessEvent chessEvent = null;
			if (pvp.needSplit()) {
				chessEvent = new PieceSplitEvent((CombinePiece)piece, 
						pvp.getSelectedPieces(), tagetPosition);
			} else {
				chessEvent = new PieceMovedEvent(prevPosition, tagetPosition);
			}
			
			if (chessEvent != null) 
			{
				ChessEventDispatcher.getInstance()
					.fireEvent(chessEvent);
			}
			
			event.dropComplete(true);
			this.square.validate();
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			event.rejectDrop();
		}
	}

}
