package chess.pieces;

import java.util.*;

import chess.board.*;

public class Rook extends Piece {
    public Rook(Alliance alliance, String imgPath, Tile location) {
        super(alliance, imgPath, location);
        this.hashValue = 5;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board, Tile start) {
        ArrayList<Move> rookMoves = new ArrayList<Move>();

        // Check 'standard rook move'
        checkRankAndFile(rookMoves, board, start);

        // Check Castling moves
        
        return rookMoves;
    }
}
