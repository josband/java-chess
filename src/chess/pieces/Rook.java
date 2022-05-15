package chess.pieces;

import java.util.*;

import chess.board.*;

public class Rook extends Piece {
    private boolean firstMove;

    public Rook(Alliance alliance, String imgPath, Tile location) {
        super(alliance, imgPath, location);
        this.hashValue = 5;
        this.firstMove = true;
    }

    public boolean firstMove() {
        return this.firstMove;
    }

    public void setMoved() {
        this.firstMove = false;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board, Tile start) {
        ArrayList<Move> rookMoves = new ArrayList<Move>();
        checkRankAndFile(rookMoves, board, start);
        return rookMoves;
    }
}
