package chess.gui;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import chess.board.*;

// Probably will change this to be a button or I will do drag and drop. IDK yet
public class TilePanel extends JPanel {
    private Tile tile;
    private int x;
    private int y;

    private static final Color WHITE_COLOR = new Color(0xFFFBF1);
    private static final Color BLACK_COLOR = new Color(0x1234567);

    // Will need a JLabel of the piece Image Icon that will be centered
    public TilePanel(Tile tile) {
        super(new GridBagLayout());
        this.tile = tile;
        this.x = tile.getX();
        this.y = tile.getY();
        setColor();

        JLabel label = new JLabel();
        if (tile.getPiece() != null) {
            label.setIcon(tile.getPiece().Image());
            label.setLayout(null);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);;
        }
        
        this.add(label);
        this.setBounds(x * 80, y * 80, 80, 80);
        this.setOpaque(true);
    }

    private void setColor() {
        if ((x + y) % 2 == 0) {
            this.setBackground(WHITE_COLOR);
        } else {
            this.setBackground(BLACK_COLOR);
        }
    }

    public Tile getTile() {
        return this.tile;
    }
}
