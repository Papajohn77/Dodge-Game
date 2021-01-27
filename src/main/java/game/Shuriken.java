package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// A self-drawable 'shuriken' represented by a character.
public class Shuriken extends Drawable {

    // The weight of the shuriken.
    private int weight;

    // The 'x' current coordinate of the shuriken.
    private int coordX;

    // The 'y' current coordinate of the shuriken.
    private int coordY;

    // The character to be displayed as a shuriken.
    private char displayChar;

    // The Frame the shuriken is attached to.
    private Frame frame;

    // The Panel we draw the shurikens onto.
    private Panel panel;

    public Shuriken(Panel p, Frame f, char c, int w) {
        super(p);

        frame = f;
        panel = p;

        /* Bounds is a field of Drawable (superclass) and it's a Rectangle,
           the Rectangle within we can draw.
           coordX and coordY are the initial coordinates of the created shuriken.*/
        coordX = (int) (bounds.width * Math.random());
        coordY = 0;

        // This will be the *
        displayChar = c;

        // That affects how fast the shuriken is gonna move downwards.
        weight = w;
    }

    /* It draws one shuriken, but its called repeatedly from the Panel 
       (with paintComponent()) to draw each shuriken. */
    @Override
    public void draw(Graphics g) {
        // Go back to the top when hitting the bottom.
        if (coordY >= bounds.height) {
            coordY = 0;
        }

        // Draw the shuriken at its current coordinates.
        g.setColor(Color.black);
        g.drawString((Character.valueOf(displayChar)).toString(),
        coordX, coordY);

        // If the shuriken hits the box we lose.
        if (new Rectangle(coordX, coordY, 5, 5).intersects(
                new Rectangle(panel.getBoxX(), Box.BOX_Y, 30, 30))) {

            Gameplay.play = false;
            Gameplay.resetScore = 0;

            /* If we don't clear the vector that contain the shurikens, then when we
               start a new round it will continue with the shurikens from the previous
               round. */
            frame.getDrawables().clear();
        }

        // Moves the shuriken left and right (or remains the same).
        switch (coordY % 3) {
        case 1:
            coordX = coordX - 5;
            break;
        case 2:
            coordX = coordX + 5;
            break;
        default:
            break;
        }

        // Move down, based on the weight.
        coordY += (int)(Math.random() * weight);
    }
}