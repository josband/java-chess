package chess.pieces;

public class Rook {
    public Rook(Alliance alliance) {
        super(alliance);
    }

    public List<Move> calculateLegalMoves(Board board, Tile start) {
        ArrayList<Move> rookMoves = new ArrayList<Move>();

        checkRankAndFile(rookMoves, board, start);
        
        return rookMoves;
    }
}
