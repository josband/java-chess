package chess.gui;

import java.awt.*;
import javax.swing.*;
import chess.Game;
import chess.board.Board;

// New Territory, will implement when there is more functionality
public class GameFrame extends JFrame {
    private Game game;
    
    public GameFrame() {
        this.game = new Game();
        setTitle("Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.DARK_GRAY);
        setIconImage((new ImageIcon("./src/chess/pieces/piece PNGs/bN.png")).getImage());
        setLayout(null);
        setResizable(true);

        Board board = this.game.getBoard();
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                TilePanel tile = new TilePanel(board.get(row, col));
                add(tile);
            }
        }

        setSize(720, 720);
        // pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
