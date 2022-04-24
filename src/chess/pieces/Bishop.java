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

    private void checkDiagonal(List<Move> bishopMoves, Board board, Tile start, int deltaX, int deltaY) {
        int currX = start.getX();
        int currY = start.getY();

        boolean done = false;
        do {
            currX += deltaX;
            currY += deltaY;
            
            try { 
                if (!board[currY][currX].isOccupied() || board[currY][currX].getPiece().getAlliance() != this.alliance) {
                    bishopMoves.add(new Move(start, board[currY][currX]));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }
            
        } while (!done);
    }
}
