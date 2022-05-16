package chess.pieces;

import java.util.*;
import chess.board.*;

public class King extends Piece {
    private boolean firstMove;
    private BoardManager manager;

    public King(Alliance alliance, String imgPath, Tile location) {
        super(alliance, imgPath, location);
        this.firstMove = true;
        this.hashValue = 9;
        this.manager = null;
    }

    public void addManager(BoardManager manager) {
        if (this.manager == null)
            this.manager = manager;
    }

    public boolean setMoved() {
        return this.firstMove = false;
    }

    public boolean firstMove() {
        return this.firstMove;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board, Tile start) {
        List<Move> kingMoves = new ArrayList<Move>();
        int kingX, kingY;

        kingX = start.getX();
        kingY = start.getY();

        for (int i = -1; i < 2; i++) {
            try {
                if (!board.get(kingY + 1, kingX + i).isOccupied() || board.get(kingY + 1, kingX + i).getPiece().getAlliance() != this.alliance) {
                    kingMoves.add(new Move(start, board.get(kingY + 1, kingX + i), this));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
            
            try {
                if (i != 0 && (!board.get(kingY, kingX + i).isOccupied() || board.get(kingY, kingX + i).getPiece().getAlliance() != this.alliance)) {
                    kingMoves.add(new Move(start, board.get(kingY, kingX + i), this));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            try {
                if (!board.get(kingY - 1, kingX + i).isOccupied() || board.get(kingY - 1, kingX + i).getPiece().getAlliance() != this.alliance) {
                    kingMoves.add(new Move(start, board.get(kingY - 1, kingX + i), this));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        manager.addCastlingMoves(alliance, kingMoves);
        return kingMoves;
    }
}
