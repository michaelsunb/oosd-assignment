package chess.mvc.views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

import chess.core.Game;
import chess.core.Piece;
import chess.mvc.models.PieceMovedEvent;
import chess.mvc.models.PieceSplitEvent;
import chess.prototype.composite.CombinePiece;
import chess.prototype.composite.Rook;
import chess.prototype.observer.ChessEvent;
import chess.prototype.observer.ChessEventDispatcher;

// http://www.dreamincode.net/forums/topic/209966-java-drag-and-drop-tutorial-part-1-basics-of-dragging/
@SuppressWarnings("serial")
public class Square extends JPanel {
	private JLabel lblIcon = new JLabel();
	private Piece piece;
	private int position;
	
	// drag & drop implementation
	private DragSource source;
	
	private TransferHandler t;
	
	public Square() {
		this.setLayout(new GridLayout(1, 1));
		this.add(this.lblIcon);
	}
	
	public Square(int pos) {
		this();
		this.position = pos;
	}
	
	public void empty() {
		this.piece = null;
		this.lblIcon.setIcon(null);
		
		t = null;
	}
	

	public void draw(Piece piece) {
		if (piece != null) {
			String pieceName = piece.getClass().getSimpleName();
			
			if (piece.getOwner() != null) {
				
				this.piece = piece;
				
				if (piece.getOwner().getColour() == Color.BLACK) {
					pieceName += ".BLACK";
				} else {
					pieceName += ".WHITE";
				}
			}
			
			String iconFle = GetExecutionPath() + File.separator + pieceName  + ".png";
			
			BufferedImage rawImage;
			try {
				rawImage = ImageIO.read(new File(iconFle));
				
				this.removeAll();
				ImageIcon icon = new ImageIcon(rawImage);
				this.lblIcon = new JLabel(icon, JLabel.CENTER);
				this.add(this.lblIcon);
				
			} catch (IOException e) {
				System.out.println(iconFle + " not found!");
			}
		}
		
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public void changeColor(BufferedImage img, Color color) {
	    for (int x = 0; x < img.getWidth(); x++) {
	        for (int y = 0; y < img.getHeight(); y++) {
	            if (img.getRGB(x, y) == Color.BLACK.getBlue())
	                img.setRGB(x, y, color.getRGB());
	        }
	    }
	}

    private String GetExecutionPath(){
    	String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
    	try {
    		// Needed in case path has spaces
    		return URLDecoder.decode(new File(absolutePath).getParent(), "UTF-8");
    	} catch (UnsupportedEncodingException e) {
    		// It's okay. Just return normally
    	}
    	return absolutePath;
    }

    @Override
    public boolean equals(Object obj) {
    	if (!(obj instanceof Square)) return false;
    	return ((Square)obj).getPosition() == this.position;
    }
}