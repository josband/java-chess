package chess.player;

import java.util.*;

import chess.pieces.*;

public class Player {
    private Alliance alliance;
    private List<Piece> pieces;

    public Player(Alliance alliance) {
        this.alliance = alliance;
        pieces = new ArrayList<Piece>();
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
}
