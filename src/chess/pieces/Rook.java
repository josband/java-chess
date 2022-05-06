package chess.pieces;

import java.util.*;

import chess.board.*;

public class Rook extends Piece {
    public Rook(Alliance alliance, String imgPath, Tile location) {
        super(alliance, imgPath, location);
    }

    public List<Move> calculateLegalMoves(Board board, Tile start) {
        ArrayList<Move> rookMoves = new ArrayList<Move>();

        checkRankAndFile(rookMoves, board, start);
        
        return rookMoves;
    }
}
