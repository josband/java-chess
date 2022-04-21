package chess.board;

import chess.pieces.Piece;

public class Tile {
    private final int x;    // We want the tile position to be immutable
    private final int y;
    private Piece piece;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.piece = null;
    }

    public Tile(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isOccupied() {
        return piece != null;
    }
}
