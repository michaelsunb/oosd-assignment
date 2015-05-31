package chess.mvc.views;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import chess.core.Game;
import chess.core.Piece;
import chess.mvc.controllers.GameController;
import chess.prototype.composite.CombinePiece;

public class PieceViewPanel extends JPanel {
	private JList<Piece> list;
	private Piece currentPiece;

	/**
	 * @pre.condition: Instantiate the class with GameController
	 * @post.condition: Class is instantiated with an empty list
	 */
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
		list = new JList<Piece>();
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setCellRenderer(new PieceListCellRenderer());
		scrollPane.setViewportView(list);
		
		this.add(scrollPane, c);
		
		c.gridy++;
	}

	/**
	 * @pre.condition: Input piece value
	 * @post.condition: sets the list from input piece
	 */
	public void setSelectPiece(Piece piece) {
		// update
		this.list.clearSelection();
		this.list.setModel(new PieceListModel(piece));
	}

	/**
	 * @pre.condition:  List needs to be set
	 * @post.condition: Return true/false if selected piece
	 * needs to be split
	 */
	public boolean needSplit() {
		this.currentPiece = Game.getInstance()
				.getBoardInstance()
				.getPiece(Game.getInstance().getSelPosition());
		
		boolean isCombinedPiece = (this.currentPiece instanceof CombinePiece);
		if (!isCombinedPiece) return false;
		
		int numPiece = ((CombinePiece)this.currentPiece).getPieces().size();
		int selPiece = this.list.getSelectedValuesList().size();

		return (numPiece != selPiece) && (selPiece != 0);
	}

	/**
	 * @pre.condition: Needs a list of pieces
	 * @post.condition: Returns a piece or pieces
	 */
	public Piece getSelectedPieces() {
		CombinePiece composite = new CombinePiece();
		
		for(Piece p : this.list.getSelectedValuesList()) {
			if(this.list.getSelectedValuesList().size() == 1) {
				return p;
			}
			composite.add(p);
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
