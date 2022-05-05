package chess.pieces;

import java.util.*;

import chess.board.*;

public class Bishop extends Piece {
    public Bishop(Alliance alliance, String imgPath) {
        super(alliance, imgPath);
    }

    public List<Move> calculateLegalMoves(Board board, Tile start) {
        ArrayList<Move> bishopMoves = new ArrayList<Move>();

        // Bishops move diagonally but CANNOT jump pieces. Therefore, I must
        // Travel a diagonally until I encounter another piece or hit the end of the board.
        // Legal moves consist of empty tiles and tiles with enemy pieces

        checkDiagonal(bishopMoves, board, start, 1, 1);
        checkDiagonal(bishopMoves, board, start, 1, -1);
        checkDiagonal(bishopMoves, board, start, -1, -1);
        checkDiagonal(bishopMoves, board, start, -1, 1);

        return bishopMoves;
    }
}
