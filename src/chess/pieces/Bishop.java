package chess.pieces;

import java.util.ArrayList;

import chess.board.Board;
import chess.board.Tile;

public class Bishop {
    public Bishop(Alliance alliance) {
        super(alliance);
    }

    public List<Move> calculateLegalMoves(Board borad, Tile start) {
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
