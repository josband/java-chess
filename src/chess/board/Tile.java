package chess.board;

import chess.pieces.Piece;

public class Tile {
    
    // A tile will contain a piece and have a coordinate within a matrix
    // TODO: Handle GUI
    private Piece piece;
    private final int x;
    private final int y;

    public Tile(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
