package chess.pieces;

public class Rook {
    public Rook(Alliance alliance, String imgPath) {
        super(alliance, imgPath);
    }

    public List<Move> calculateLegalMoves(Board board, Tile start) {
        ArrayList<Move> rookMoves = new ArrayList<Move>();

        checkRankAndFile(rookMoves, board, start);
        
        return rookMoves;
    }
}
