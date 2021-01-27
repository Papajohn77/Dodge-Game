package game;

import javax.swing.JOptionPane;

public class Gameplay {

    /* Number of shurikens. */
    private static final int NUM_OF_SHURIKENS = 240;

    /* The state of the game. */
    static boolean play = false;

    // The score is equal to the number of shurikens that are currently on the field.
    static int score = 0;

    /* We need that in order to not reset the score field when we lose, we dont want to 
       reset the score field because we want to display the previous round score until
       the player decide to start a new round. */
    static int resetScore = 0;

    // TODO: remove magic numbers from all classes.
    public static void main(String[] args) {
        // Create a window and the canvas to draw onto.
        Frame d = new Frame();

        createShurikens(d, NUM_OF_SHURIKENS);
    }

    // Creates the shurikens, and adds them to the Frame's Vector of drawable objects.
    private static void createShurikens(Frame d, int numOfShurikens) {

        for (int i = 0; i < numOfShurikens; i++) {

            if(play) {
                if (resetScore == 0) {
                    /* We need that in order to make sure that even if the player instantly
                       pressed the left or the right arrow after he lost (so the else didn't
                       executed) the number of created shurikens is gonna be reseted.*/
                    i = 0;

                    // Restarts the score when the player start a new round.
                    score = 0;

                    /* Changes resetScore value in order to enter again the if statement
                       only if we lose. */
                    resetScore = 1;
                }

                score++;

                switch (i % 4) {
                    case 0:
                    case 1:
                        // This type of shuriken is gonna be created twice as much.
                        d.addDrawObject(new Shuriken(d.getCanvas(), d, '*', 21));
                        break;
                    case 2:
                        d.addDrawObject(new Shuriken(d.getCanvas(), d, '*', 14));
                        break;
                    case 3:
                        d.addDrawObject(new Shuriken(d.getCanvas(), d, '*', 7));
                        break;
                } 
            } else {
                /* If the state of the game (play) is false we need to keep the
                   loop alive, otherwise the loop will finish and then when we will
                   try to start a new round no shurikens are gonna be created.*/
                i = 0;
            }

            try {
                // Allow existing snowflakes to fall a bit, before adding more.
                // This thread refers to the main thread.
                Thread.sleep(2500);
            } catch (InterruptedException e) {

            }
        }

        /* If someone reaches the 240 score (that means that all of the shurikens are
           created) then he win the game.
           This process is gonna take arround 10 minutes.*/
        JOptionPane.showMessageDialog(null, "            YOU WON!!!", "Congratulations!!!", 1);
        System.exit(0);
    }
}