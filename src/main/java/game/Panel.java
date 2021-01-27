package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

public class Panel extends JPanel {

    // The Frame the Panel is attached to.
    private Frame controller = null;

    // The box.
    private Box box;

    // Get the x coordinate of the box that is attached to the panel.
    public int getBoxX() {
        return box.getBoxX();
    }

    // Required, because JPanel implements serializable.
    private static final long serialVersionUID = 1L;

    // Constructor to initialize the DrawablePanel with it's controller.
    public Panel(Frame frame, Box box) {
        controller = frame;
        this.box = box;
        addKeyListener(box);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
    }

    /*
     * Perform all drawing operations by overriding the JPanel method
     * and initiating all the drawing from this place we take advantage
     * of JPanel's double-buffering capability.
     *
     * This method is called from the Frame's thread to repaint everything 
     * on the Panel!
`    */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Frame.BACKGROUNDCOLOUR);

        // Draw box.
	g.setColor(Color.black);
	g.fillRect(box.getBoxX(), Box.BOX_Y, 30, 30);

        // Draw score.
        g.setColor(Color.white);
	g.setFont(new Font("serif", Font.BOLD, 25));
	g.drawString(String.valueOf(Gameplay.score), 590, 30);

        // Draws the message we display when the state of the game is false.
        if (!Gameplay.play) {
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score: " + Gameplay.score, 215, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            String leftArrow = "\u25C4";
            String rightArrow = "\u25BA";
            g.drawString("Press " + leftArrow + " or " + rightArrow + " to Restart",
                    245, 350);
        }

        // Draw the shurikens.
        g.setFont(new Font("serif", Font.BOLD, 30));
        Vector<Drawable> toPaint = controller.getDrawablesCopy();
        for (Drawable d : toPaint) {
            d.draw(g);
        }
    }
}