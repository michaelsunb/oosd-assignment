import javax.swing.SwingUtilities;

import chess.mvc.views.MainFrame;


public class Chess {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
			}

		});
	}

}
