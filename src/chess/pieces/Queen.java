package chess.pieces;

import java.util.ArrayList;

public class Queen {
    public Queen(Alliance alliance, String imgPath) {
        super(alliance);
    }

    public List<Move> calculateLegalMoves(Board board, Tile start) {
        List<Move> queenMoves = new ArrayList<Move>();

        checkRankAndFile(queenMoves, board, start);

        checkDiagnol(queenMoves, board, start, 1, 1);
        checkDiagnol(queenMoves, board, start, 1, -1);
        checkDiagnol(queenMoves, board, start, -1, -1);
        checkDiagnol(queenMoves, board, start, -1, 1);
    }
}
