package caffeine.view;


import java.awt.*;
import javax.swing.*;

import caffeine.Game;
/**
   A panel for displaying the board.
 */

@SuppressWarnings("serial")
public class BoardView extends JPanel{
	private Camera cam;
	private Game game;
	/**
       Creates a view from a board.
	 */
	public BoardView(Game game) {
		this.game = game;
		cam = new Camera();
		setBackground(Color.BLACK);
	}
	
	public Camera getCamera(){
		return cam;
	}

	/**
       Paints the panel.
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		cam.view(g2, game.getWorld().get(0).getSprites());
	}

	/**
       Returns the preferred size of this panel.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(cam.depthOfField,cam.depthOfField);
	}  
}

