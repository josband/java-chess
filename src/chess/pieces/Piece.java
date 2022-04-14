package chess.pieces;

import chess.board.Tile;

public abstract class Piece {
    final int value;

    public Piece(int value) {
        this.value = value;
        this.dead = false;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public abstract boolean isValidMove(Tile from, Tile to);
}
