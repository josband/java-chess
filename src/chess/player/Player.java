package chess.player;

import java.util.ArrayList;

import chess.pieces.Alliance;

public class Player {
    private Alliance alliance;
    private List<Piece> pieces;

    public Player(Alliance alliance) {
        this.alliance = alliance;
        pieces = new ArrayList<Pieces>();
    }

    public Alliance getAlliance() {
        return this.alliance;
    }

    public void addPieces(Piece piece) {
        pieces.add(piece);
    }

    public void addPieces(List<Piece> pieces) {
        this.pieces.add(pieces);
    }
}
