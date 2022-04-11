package chess.board;

public abstract class Piece {
    private boolean dead;
    final int value;

    public Piece(int value) {
        this.value = value;
        this.dead = false;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public abstract boolean isValidMove(Tile from, Tile to);
}
