package chess.mvc.views;

import java.awt.FlowLayout;
import javax.swing.*;

public class CommandsPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 774350769693191550L;
	private JButton startBtn;
	private JButton exitBtn;
	
	public CommandsPane() {
		this.setLayout(new FlowLayout());
		
		startBtn = new JButton("Start");
		this.add(startBtn);
		
		exitBtn = new JButton("Exit");
		this.add(exitBtn);
	}
	
}
