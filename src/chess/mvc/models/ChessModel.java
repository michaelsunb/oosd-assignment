package chess.mvc.models;

import javax.swing.JButton;

import chess.mvc.views.ChessboardViewPanel;

public class ChessModel {
	
	private int position;
	private  JButton btn;
	private ChessboardViewPanel cvp;
	
	
	
	
	public ChessboardViewPanel getCvp() {
		return cvp;
	}

	public void setCvp(ChessboardViewPanel cvp) {
		this.cvp = cvp;
	}

	public ChessModel(int position, JButton btn, ChessboardViewPanel cvp) {
		this.setBtn(btn);
		this.setPosition(position);
		this.setCvp(cvp);
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public JButton getBtn() {
		return btn;
	}

	public void setBtn(JButton btn) {
		this.btn = btn;
	}
	
	
}
