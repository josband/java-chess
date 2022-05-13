package chess.gui;

import javax.swing.*;
import chess.board.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    private Board boardRef;
    
    public BoardPanel(Board board) {
        super(new GridLayout(8,8));
        this.boardRef = board;
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j++) {
                this.add(new TilePanel(board.get(i, j)));
            }
        }

        this.setSize(640, 640);
        this.setOpaque(true);
    }
}
