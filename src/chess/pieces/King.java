package chess.pieces;

import java.util.*;
import chess.board.*;

public class King extends Piece {
    private boolean inCheck;

    public King(Alliance alliance, String imgPath, Tile location) {
        super(alliance, imgPath, location);
        inCheck = false;
    }

    public boolean setCheck(boolean check) {
        return this.inCheck = check;
    }

    public boolean isChecked() {
        return this.inCheck;
    }

    public List<Move> calculateLegalMoves(Board board, Tile start) {
        List<Move> kingMoves = new ArrayList<Move>();
        int kingX, kingY;

        kingX = start.getX();
        kingY = start.getY();

        for (int i = -1; i < 2; i++) {
            try {
                if (board.get(kingY + 1, kingX + i).getPiece() == null || board.get(kingY + 1, kingX + i).getPiece().getAlliance() != this.alliance) {
                    kingMoves.add(new Move(start, board.get(kingY + 1, kingX + i)));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }

            try {
                if (board.get(kingY - 1, kingX + i).getPiece() == null || board.get(kingY - 1, kingX + i).getPiece().getAlliance() != this.alliance) {
                    kingMoves.add(new Move(start, board.get(kingY - 1, kingX + i)));
                }
            } catch (ArrayIndexOutOfBoundsException e) {      
            }
        }

        return kingMoves;
    }
}
