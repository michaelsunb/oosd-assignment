package chess.mvc.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.ArrayList;

import javafx.scene.control.SelectionMode;

import javax.swing.*;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import chess.core.Piece;
import chess.mvc.controllers.GameController;
import chess.prototype.composite.CombinePiece;

//https://www.google.com.au/search?q=mouselistener+right+click&oq=mouselistener+right+click&aqs=chrome..69i57.6831j0j4&sourceid=chrome&ie=UTF-8#q=java+right+click+mouse+event+popup+menu
//http://stackoverflow.com/questions/9368147/right-clicking-on-jbutton
//http://stackoverflow.com/questions/766956/how-do-i-create-a-right-click-context-menu-in-java-swing
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
		btnSplit = new JButton("Split");
//		btnSplit.setActionCommand("PieceSplitEvent");
//		btnSplit.addActionListener(handler.eventSequence(-1));
		this.add(btnSplit, c);
	}

	public void setSelectPiece(Piece piece) {
		this.list.setModel(new PieceListModel(piece));
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
