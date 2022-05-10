package chess.board;

import chess.pieces.Piece;

public class Move {
    private Tile from;
    private Tile to;
    private Piece piece; // Maybe to removing pieces. I can know what the piece is or hashing a move

    public Move(Tile from, Tile to) {
        this.from = from;
        this.to = to;
    }

    public Tile getFrom() {
        return from;
    }

    public Tile getTo() {
        return to;
    }

    public Piece pieceMoved() {
        return this.piece;
    }

    @Override
    public int hashCode() {
        return piece.getValue() * (this.from.hashCode() + this.to.hashCode());
    }
}
