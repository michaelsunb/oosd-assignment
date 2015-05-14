package chess.mvc.views;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.*;

import chess.core.Piece;
import chess.mvc.controllers.GameController;
import chess.prototype.composite.CombinePiece;

public class PieceViewPanel extends JPanel {
	private JList list;
	private JButton btnSplit;

	public PieceViewPanel(GameController handler) {
		this.setLayout(new GridLayout(0, 1));

		JScrollPane scrollPane = new JScrollPane();
		list = new JList();
		scrollPane.setViewportView(list);

		this.add(scrollPane);

		btnSplit = new JButton("Split");
//		btnSplit.setActionCommand("PieceSplitEvent");
//		btnSplit.addActionListener(handler.eventSequence(-1));

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

// https://www.google.com.au/search?q=mouselistener+right+click&oq=mouselistener+right+click&aqs=chrome..69i57.6831j0j4&sourceid=chrome&ie=UTF-8#q=java+right+click+mouse+event+popup+menu
// http://stackoverflow.com/questions/9368147/right-clicking-on-jbutton
// http://stackoverflow.com/questions/766956/how-do-i-create-a-right-click-context-menu-in-java-swing