package chess.gui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import chess.board.*;

// Probably will change this to be a button or I will do drag and drop. IDK yet
public class TilePanel extends JPanel {
    private Tile tile;

    // Will need a JLabel of the piece Image Icon that will be centered
    public TilePanel(Tile tile) {
        this.tile = tile;
        int x = tile.getX();
        int y = tile.getY();
        if ((x + y) % 2 == 0) {
            this.setBackground(new Color(255, 255, 255));
        } else {
            this.setBackground(new Color(0x1234567));
        }

        JLabel label = new JLabel();
        if (tile.getPiece() != null) {
            label.setIcon(tile.getPiece().Image());
            label.setLayout(null);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.BOTTOM);;
        }

        this.add(label);
        this.setBounds(x * 80, y * 80, 80, 80);
        this.setOpaque(true);
    }

    public Tile getTile() {
        return this.tile;
    }
}
