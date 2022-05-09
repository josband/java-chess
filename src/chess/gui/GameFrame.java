package chess.gui;

import java.awt.*;
import javax.swing.*;

// New Territory, will implement when there is more functionality
public class GameFrame extends JFrame {  
    public GameFrame() {
        super("Java Chess"); // Make frame with title to hold contents of game within it
        setVisible(true); // Makes frame visible
        setSize(1000, 650); // Sets size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exits application on close
        
        ImageIcon logo = new ImageIcon("./src/chess/pieces/piece PNGs/NBlack.png"); // Add logo for the game
        setIconImage(logo.getImage());
        getContentPane().setBackground(Color.DARK_GRAY); // Sets Background color
        
        
        // pack(); // sets frame size to accomodate all of the components within the contentPane
    }
}
