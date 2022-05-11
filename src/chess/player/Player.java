package chess.player;

import java.util.*;

import chess.board.Board;
import chess.pieces.*;

public class Player {
    private Alliance alliance;
    private List<Piece> pieces;

    public Player(Alliance alliance, Board board) {
        this.alliance = alliance;
        this.pieces = new LinkedList<Piece>();
        initializePieceList(board);
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

    public King getKing() {
        assert pieces.get(8) instanceof King;
        
        return (King) pieces.get(8);
    }
}
