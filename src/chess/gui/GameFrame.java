package chess.gui;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// New Territory, will implement when there is more functionality
public class GameFrame extends JFrame {  
    public GameFrame() {
        super("Java Chess");
        getContentPane().setBackground(Color.DARK_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1000, 650);
        setVisible(true);
        try {
            BufferedImage logo = ImageIO.read(new File("./src/chess/pieces/piece PNGs/NBlack.png"));
            setIconImage(logo);
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
            System.exit(1);
        }
    }
}
