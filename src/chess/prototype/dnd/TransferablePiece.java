/*
 * Author: Sokun, CHORN
 * Number: s3455783
 */
package chess.prototype.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import chess.core.*;
import chess.prototype.composite.*;

public class TransferablePiece implements Transferable {
	private Piece piece;
	public static final DataFlavor[] flavors = new DataFlavor[] { 
		new DataFlavor(Rook.class, Rook.class.getSimpleName()),
		new DataFlavor(Knight.class, Knight.class.getSimpleName()),
		new DataFlavor(Bishop.class, Bishop.class.getSimpleName()),
		new DataFlavor(Musician.class, Musician.class.getSimpleName()),
		new DataFlavor(Jester.class, Jester.class.getSimpleName()),
		new DataFlavor(CombinePiece.class, CombinePiece.class.getSimpleName())
	};
	
	public TransferablePiece(Piece piece) {
		this.piece = piece;
	}
	
	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		
		if (!supportFlavor(flavor)) 
			throw new UnsupportedFlavorException(flavor);
		
		return this.piece;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return supportFlavor(flavor);
	}

	private boolean supportFlavor(DataFlavor flavor) {
		for(DataFlavor f: this.flavors) {
			if (f.equals(flavor)) return true;
		}
		return false;
	}
}
