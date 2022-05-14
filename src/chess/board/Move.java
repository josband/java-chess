package chess.board;

import chess.pieces.Piece;

public class Move {
    private Tile from;
    private Tile to;
    private Piece piece; // Maybe to removing pieces. I can know what the piece is or hashing a move
    private Move subMove;

    public Move(Tile from, Tile to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
    }

    public Move(Tile from, Tile to, Piece piece, Move subMove) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.subMove = subMove;
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

    public boolean hasSubMove() {
        return this.subMove != null;
    }

    public Move getSubMove() {
        return this.subMove;
    }

    public Piece getMovedPiece() {
        return this.piece;
    }

    @Override
    public int hashCode() {
        return piece.getValue() * (this.from.hashCode() + this.to.hashCode());
    }
}
