package chess.mvc.views;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chess.core.Piece;

public class Square extends JPanel {
	private Piece piece = null;
	private JLabel lblIcon = new JLabel();
	
	public Square() {
		this.setLayout(new GridLayout(1, 1));
		this.add(this.lblIcon);
	}
	
	public void empty() {
		this.lblIcon.setIcon(null);
	}
	
	public void draw(Piece piece) {
		if (piece != null) {
			String pieceName = piece.getClass().getSimpleName();
			
			if (piece.getOwner() != null) {
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
	
	public void changeColor(BufferedImage img, Color color) {
	    for (int x = 0; x < img.getWidth(); x++) {
	        for (int y = 0; y < img.getHeight(); y++) {
	            if (img.getRGB(x, y) == Color.BLACK.getBlue())
	                img.setRGB(x, y, color.getRGB());
	        }
	    }
	}
	
	// http://stackoverflow.com/questions/16054596/change-color-of-non-transparent-parts-of-png-in-java
//    private static BufferedImage colorImage(BufferedImage image, Color color) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//
//        for (int xx = 0; xx < width; xx++) {
//            for (int yy = 0; yy < height; yy++) {
//            	if (!(image.getRGB(xx, yy) == Color.BLACK.getRGB())) continue;
//
//            	image.setRGB(xx, yy, color.getRGB());
//            }
//        }
//        return image;
//    }
//    
    private String GetExecutionPath(){
        String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
       return new File(absolutePath).getParent();
    }
}