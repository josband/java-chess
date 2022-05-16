package chess.board;

import chess.pieces.Piece;

public class Move {
    private final Tile from;
    private final Tile to;
    private final Piece piece; // Maybe to removing pieces. I can know what the piece is or hashing a move
    private final boolean kingCastleMove;
    private final boolean queenCastleMove;
    private final boolean enPassant;

    public Move(Tile from, Tile to, Piece piece) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.kingCastleMove = false;
        this.queenCastleMove = false;
        this.enPassant = false;
    }

    public Move(Tile from, Tile to, Piece piece, boolean kingCastleMove, boolean queenCastleMove) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.kingCastleMove = kingCastleMove;
        this.queenCastleMove = queenCastleMove;
        this.enPassant = false;
    }

    public Move(Tile from, Tile to, Piece piece, boolean enPassant) {
        this.from = from;
        this.to = to;
        this.piece = piece;
        this.kingCastleMove = false;
        this.queenCastleMove = false;
        this.enPassant = enPassant;
    }

    public Tile getFrom() {
        return from;
    }

    public Tile getTo() {
        return to;
    }

    public boolean isCastling() {
        return this.queenCastleMove || this.kingCastleMove;
    }

    public boolean isKingCastling() {
        return this.kingCastleMove;
    }

    public boolean isQueenCastling() {
        return this.queenCastleMove;
    }

    public boolean isEnPassant() {
        return this.enPassant;
    }

    public Piece getMovedPiece() {
        return this.piece;
    }

    @Override
    public int hashCode() {
        return piece.getValue() * (this.from.hashCode() + this.to.hashCode());
    }
}
