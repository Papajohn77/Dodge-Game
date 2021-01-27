package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Box  implements KeyListener{

    // The coordinate X of the box.
    private static int boxX = 335;

    public int getBoxX() {
        return boxX;
    }

    /* The coordinate Y of the box, it's final because the box its only moving
       horizontally.*/
    public static final int BOX_Y = 530;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    /* When the player press the right or the left arrow, we move the box to the same 
       direction, the box isn't allow to move out of the canvas*/
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (boxX + 30 > 680) {
                boxX = 655;
            } else {
                Gameplay.play = true;
                boxX += 20;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (boxX < 10) {
                boxX = 0;
            } else {
                Gameplay.play = true;
                boxX -= 20;
            }
        }
    }
}