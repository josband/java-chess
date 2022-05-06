package chess.gui;

import java.awt.*;
import javax.swing.*;

import chess.Game;

// New Territory, will implement when there is more functionality
public class GameFrame extends JFrame{
    public GameFrame() {
        super("Java Chess");
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(750, 750);
        setVisible(true);
    }
}
