package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * The program's main window.
 * Extends JFrame to display the window where the
 * shurikens are drawn. Implements the {@link java.lang.Runnable Runnable}
 * interface so as to create a thread that repeatedly calls the
 * {@link game.Drawable#draw() draw}method.
 */
public class Frame extends JFrame implements Runnable {

    /** The window's width. */
    public static final int WIDTH = 700;
    /** The window's height. */
    public static final int HEIGHT = 600;

    // The window's background color (gray).
    public static final Color BACKGROUNDCOLOUR = new Color(160, 160, 160);

    // Contains the shurikens!
    private Vector<Drawable> drawObjects = null;

    // The drawing thread.
    private Thread thread;

    // The canvas to draw onto.
    private Panel drawablePanel = null;

    // Required, because JFrame implements serializable.
    static final long serialVersionUID = 1L;

    // Constructor to initialize and display the window and starts the animation.
    public Frame() {
        // Sets title
        super(String.format("Avoid the shurikens!"));

        // Sets the icon of the window.
        ImageIcon icon = new ImageIcon(
                getClass().getClassLoader().getResource("GameIconn.png"));
        setIconImage(icon.getImage());

        // Creates the vector.
        drawObjects = new Vector<Drawable>();

        initializeGraphics();
        initializeThread();
    }

    // Initialize the main window.
    private void initializeGraphics() {
        // Make our window look nice
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create the box.
        Box box = new Box();

        // Create our drawing canvas and adds it to the Frame.
        drawablePanel = new Panel(this, box);
        drawablePanel.setBackground(BACKGROUNDCOLOUR);
        drawablePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setContentPane(drawablePanel);

        // Handle termination
        setDefaultCloseOperation(
                javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Exit when the window is closed
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Our windows size
        setSize(WIDTH, HEIGHT);

        // Centers the window.     
        this.setLocationRelativeTo(null);

        // Removes the ability to change the size of the window.
        setResizable(false);

        // Displays the window
        setVisible(true);
    }

    /* Start the execution of the drawing thread. */
    private void initializeThread() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

    // Adds the shurikens that are created in gameplay.
    public void addDrawObject(Drawable drawObject) {
        drawObjects.add(drawObject);
    }

    // Return a copy of the component list to be drawn.
    public Vector<Drawable> getDrawablesCopy() {
        return new Vector<Drawable>(drawObjects);
    }

    // Return a reference to the Vector of shurikens.
    public Vector<Drawable> getDrawables() {
        return drawObjects;
    }

    /* This method is used to repaint everything on the panel every 1/4 seconds
       and it's gonna continuously run until we close the window or set the Thread
       equal to null somewhere else in the programm. */
    @Override public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            try {
                // Repaints the Panel.
                drawablePanel.repaint();
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Get the canvas's drawing panel.
    public Panel getCanvas(){
        return drawablePanel;
    }
}
