package chess.mvc.views;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.*;

import chess.core.Piece;
import chess.prototype.composite.CombinePiece;

public class PieceViewPanel extends JPanel {
	private JList list;
	private JButton btnSplit;

	public PieceViewPanel(AbstractAction handler) {
		this.setLayout(new GridLayout(0, 1));

		JScrollPane scrollPane = new JScrollPane();
		list = new JList();
		scrollPane.setViewportView(list);

		this.add(scrollPane);

		btnSplit = new JButton("Split");
		btnSplit.setActionCommand("PieceSplitEvent");
		btnSplit.addActionListener(handler);

		this.add(btnSplit);
	}

	public void setSelectPiece(Piece piece) {
		if (piece instanceof CombinePiece) {

		} else {

		}
	}

	private static class RadioButtonListCellRenderer extends
			DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list,
				Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			// TODO Auto-generated method stub
			return super.getListCellRendererComponent(list, value, index,
					isSelected, cellHasFocus);

		}

	}
}
