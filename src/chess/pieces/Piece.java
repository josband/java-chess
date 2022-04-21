package chess.pieces;

import chess.board.Tile;

public abstract class Piece {
    private final Alliance alliance;
    private boolean dead;

    public Piece(Alliance alliance) {
        this.alliance = alliance;
        this.dead = false;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead() {
        this.dead = true;
    }

    public Alliance getAlliance() {
        return alliance;
    }  
    
    public abstract List<Move> calculateLegalMoves(Board board, Tile start);
}
