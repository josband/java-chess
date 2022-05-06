package chess.pieces;

import java.util.*;
import chess.board.*;

public class King extends Piece {
    public King(Alliance alliance, String imgPath) {
        super(alliance, imgPath);
    }

    public List<Move> calculateLegalMoves(Board board, Tile start) {
        List<Move> kingMoves = new ArrayList<Move>();

        return kingMoves;
    }
}
