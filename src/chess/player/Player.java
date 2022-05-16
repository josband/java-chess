package chess.player;

import java.util.*;

import chess.board.Board;
import chess.pieces.*;

public class Player {
    private final Alliance alliance;
    private final King king;
    private final Rook queenRook;
    private final Rook kingRook;
    private final List<Piece> pieces;

    public Player(Alliance alliance, Board board) {
        this.alliance = alliance;
        this.pieces = new LinkedList<Piece>();
        initializePieceList(board);
        this.king = findKing();
        this.queenRook = findQueenRook();
        this.kingRook = findKingRook();
    }

    public Alliance getAlliance() {
        return this.alliance;
    }

    public void addPieces(Piece piece) {
        pieces.add(piece);
    }

    public void addPieces(List<Piece> pieces) {
        this.pieces.addAll(pieces);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    private void initializePieceList(Board board) {
        if (this.alliance == Alliance.BLACK) {
            for (int i = 0; i < 8; i++) {
                this.pieces.add(board.get(0, i).getPiece());
                this.pieces.add(board.get(1, i).getPiece());
            }
        } else {
            for (int i = 0; i < 8; i++) {
                this.pieces.add(board.get(7, i).getPiece());
                this.pieces.add(board.get(6, i).getPiece());
            }
        }
    }

    private King findKing() {
        assert pieces.get(8) instanceof King;
        
        return (King) pieces.get(8);
    }

    private Rook findKingRook() {
        assert pieces.get(14) instanceof Rook;

        return (Rook) pieces.get(14);
    }

    private Rook findQueenRook() {
        assert pieces.get(0) instanceof Rook;

        return (Rook) pieces.get(0);
    }

    public King getKing() {
        return this.king;
    }

    public Rook getKingRook() {
        return this.kingRook;
    }

    public Rook getQueenRook() {
        return this.queenRook;
    }
}
