/*
 * Author: Sokun, CHORN
 * Number: s3455783
 */
package chess.prototype.dnd;

import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import chess.core.Piece;
import chess.mvc.views.Square;

public class PieceDragGesture implements DragGestureListener {

	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = null;
		Square square = (Square) event.getComponent();
		
		if (event.getDragAction() == DnDConstants.ACTION_MOVE) {
			cursor = DragSource.DefaultCopyDrop;
		}
		
		Piece piece = square.getPiece();
		
		event.startDrag(cursor, new TransferablePiece(piece));
	}

}
