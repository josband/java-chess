package chess.pieces;

import java.util.ArrayList;

import chess.board.Tile;

public final class Pawn extends Piece {
    private boolean enPassant; // For later implementation
    private byte direction;

    public Pawn(Alliance alliance) {
        super(alliance);
        this.enPassant = false;
        this.direction = this.alliance == Alliance.WHITE ? 1 : -1;
    }

    public byte getDirection() {
        return this.direction;
    }

    public boolean canEnPassant() {
        return enPassant;
    }

    public void setEnPassant() {
        this.enPassant = true;
    }

    public List<Move> calculateLegalMoves(Board board, Tile start) {
        List<Move> legalMoves = new ArrayList<Move>(); // Replace with hashmap for better performance
        
        int x = start.getX();
        int y = start.getY();
        
        // Pawns can only move forward, unless there is a piece on a tile one diagonal 
        // away, in which case the pawn can capture the piece
        
        if (board[y + this.direction][x].getPiece() == null) {
            legalMoves.add(new Move(start, board[y + this.direction][x]));

            // Pawns can move two spaces if they are on their starting row
            if (this.alliance == Alliance.WHITE && y == 1 && board[y + 2 * this.direction][x].getPiece() == null) {
                legalMoves.add(new Move(start, board[y + (2 * this.direction)][x]));
            }
        }

        // Pawns can only capture diagonally if there is a piece on the tile of a different alliance
        // Need to be careful of index out of bounds
        if (x + 1 < 8 && board[y + this.direction][x + 1].isOccupied() && board[y + this.direction][x + 1].getPiece().alliance != this.alliance) {
            legalMoves.add(new Move(start, board[y + this.direction][x + 1]));
        }

        if (x - 1 > -1 && board[y + this.direction][x - 1].isOccupied() && board[y + this.direction][x - 1].getPiece().alliance != this.alliance) {
            legalMoves.add(new Move(start, board[y + this.direction][x + 1]));
        }
        
        return legalMoves;
    }
}