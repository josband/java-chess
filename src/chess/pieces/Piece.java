package chess.pieces;

import chess.board.Tile;

// Maybe add checking the diagonals and horizontal movements to this class for the 
// Queen, Rook, and Bishop to share through inheritance
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
