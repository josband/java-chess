package chess.gui;

import java.awt.*;
import javax.swing.*;
import chess.Game;
import chess.board.Board;

// New Territory, will implement when there is more functionality
public class GameFrame extends JFrame {
    private Game game;
    private final BoardPanel boardPanel;
    
    public GameFrame() {
        this.game = new Game();
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);
        getContentPane().setPreferredSize(new Dimension(640, 640));;
        setIconImage((new ImageIcon("./src/chess/pieces/piece PNGs/black-knight.png")).getImage());
        setLayout(null);
        setResizable(true);

        this.boardPanel = new BoardPanel(game.getBoard());

        this.add(boardPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
