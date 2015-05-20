package chess.mvc.views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import chess.core.Piece;
import chess.mvc.controllers.GameController;
import chess.prototype.composite.CombinePiece;

public class PieceViewPanel extends JPanel {
	private JList list;
	private JButton btnSplit;
	private Piece currentPiece;
	
	public PieceViewPanel(GameController handler) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3, 3, 3, 3);
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		this.add(new JLabel("Current Piece"), c);
		
		c.gridy++;

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(180, 80));
		list = new JList();
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setCellRenderer(new PieceListCellRenderer());
		scrollPane.setViewportView(list);
		
		this.add(scrollPane, c);
		
		c.gridy ++;
	}

	public void setSelectPiece(Piece piece) {
		// update
		this.list.clearSelection();
		this.list.setModel(new PieceListModel(piece));
	}
	
	public boolean needSplit() {
		boolean isCombinedPiece = (this.currentPiece instanceof CombinePiece);
		if (!isCombinedPiece) return false;
		
		int numPiece = ((CombinePiece)this.currentPiece).getPieces().size();
		int selPiece = this.list.getSelectedValuesList().size();
		
		boolean hasPartialPicked = (numPiece != selPiece);
		
		return hasPartialPicked;
	}
	
	public Piece getSelectedPieces() {
		CombinePiece composite = new CombinePiece();
		for(Object obj : this.list.getSelectedValuesList()) {
			composite.add((Piece)obj);
		}
		return composite;
	}
	
	private class PieceListModel extends AbstractListModel<Piece>  {
		private CombinePiece composite;
		
		public PieceListModel(Piece piece) {
			if (!(piece instanceof CombinePiece)) {
				composite = new CombinePiece();
				composite.add(piece);
			} else {
				this.composite = (CombinePiece)piece;
			}
		}
		
		@Override
		public Piece getElementAt(int index) {
			return composite.getPieces().get(index);
		}

		@Override
		public int getSize() {
			return composite.getPieces().size();
		}
		
	}
	
	private static class PieceListCellRenderer extends
			DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			
			return super.getListCellRendererComponent(list, value.getClass().getSimpleName(), index,
					isSelected, cellHasFocus);

		}

	}
}
