package chess.pieces;

import java.util.*;

import chess.board.*;

public final class Pawn extends Piece {
    private boolean enPassant; // For later implementation
    private byte direction;

    public Pawn(Alliance alliance, String imgPath) {
        super(alliance, imgPath);
        this.enPassant = false;
        this.direction = (byte) (this.alliance == Alliance.WHITE ? 1 : -1);
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
        List<Move> pawnMoves = new ArrayList<Move>(); // Replace with hashmap for better performance
        
        int x = start.getX();
        int y = start.getY();
        
        // Pawns can only move forward, unless there is a piece on a tile one diagonal 
        // away, in which case the pawn can capture the piece
        
        if (board.get(y + this.direction, x).getPiece() == null) {
            pawnMoves.add(new Move(start, board.get(y + this.direction, x)));

            // Pawns can move two spaces if they are on their starting row
            if ((this.alliance == Alliance.WHITE && y == 1 || this.alliance == Alliance.BLACK && y == 7) 
                && board.get(y + 2 * this.direction, x).getPiece() == null) {
                pawnMoves.add(new Move(start, board.get(y + 2 * this.direction, x)));
            }
        }

        // Pawns can only capture diagonally if there is a piece on the tile of a different alliance
        // Need to be careful of index out of bounds
        if (x + 1 < 8 && board.get(y + this.direction, x + 1).isOccupied() && board.get(y + this.direction, x + 1).getPiece().getAlliance() != this.alliance) {
            pawnMoves.add(new Move(start, board.get(y + this.direction, x + 1)));
        }
        
        if (x - 1 > -1 && board.get(y + this.direction, x - 1).isOccupied() && board.get(y + this.direction, x - 1).getPiece().getAlliance() != this.alliance) {
            pawnMoves.add(new Move(start, board.get(y + this.direction, x - 1)));
        }
        
        return pawnMoves;
    }
}
