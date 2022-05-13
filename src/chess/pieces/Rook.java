package chess.pieces;

import java.util.*;

import chess.board.*;

public class Rook extends Piece {
    private boolean moved;

    public Rook(Alliance alliance, String imgPath, Tile location) {
        super(alliance, imgPath, location);
        this.hashValue = 5;
        this.moved = false;
    }

    public boolean hasMoved() {
        return this.moved;
    }

    public void setCastle(boolean moved) {
        this.moved = moved;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board, Tile start) {
        ArrayList<Move> rookMoves = new ArrayList<Move>();

        // Check 'standard rook move'
        checkRankAndFile(rookMoves, board, start);

        // Will have to utilize board manager to control castling

        return rookMoves;
    }
}
