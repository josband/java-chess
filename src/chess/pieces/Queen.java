package chess.pieces;

import java.util.*;

import chess.board.*;


public class Queen extends Piece {
    public Queen(Alliance alliance, String imgPath, Tile location) {
        super(alliance, imgPath, location);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board, Tile start) {
        List<Move> queenMoves = new ArrayList<Move>();

        checkRankAndFile(queenMoves, board, start);

        checkDiagonal(queenMoves, board, start, 1, 1);
        checkDiagonal(queenMoves, board, start, 1, -1);
        checkDiagonal(queenMoves, board, start, -1, -1);
        checkDiagonal(queenMoves, board, start, -1, 1);

        return queenMoves;
    }
}
