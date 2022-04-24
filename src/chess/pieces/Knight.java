package chess.pieces;

import java.util.ArrayList;

import chess.board.Board;

public class Knight {
    public Knight(Alliance alliance) {
        super(alliance); 
    }

    public List<Move> calculateLegalMoves(Board board, Tile start) {
        // Knights move by going N, E, S, or W by 2 and then orthogonally 1
        ArrayList<Move> knightMoves = new ArrayList<Move>();
    }
}
