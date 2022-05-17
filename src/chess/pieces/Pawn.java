package chess.pieces;

import java.util.*;

import chess.board.*;

public final class Pawn extends Piece {
    private boolean enPassant; // For later implementation
    private byte direction;

    public Pawn(Alliance alliance, String imgPath, Tile location) {
        super(alliance, imgPath, location);
        this.enPassant = false;
        this.direction = (byte) (this.alliance == Alliance.WHITE ? -1 : 1);
        this.hashValue = 1;
    }

    public byte getDirection() {
        return this.direction;
    }

    public boolean canEnPassant() {
        return enPassant;
    }

    public void setEnPassant(boolean enPassant) {
        this.enPassant = enPassant;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board, Tile start) {
        List<Move> pawnMoves = new ArrayList<Move>();
        
        int x = start.getX();
        int y = start.getY();

        // In the last rank, we will have to eventually promote
        if (y + this.direction > 7 || y + this.direction < 0) {
            return pawnMoves;
        }
        
        // Pawns can only move forward, unless there is a piece on a tile one diagonal 
        // away, in which case the pawn can capture the piece

        if (board.get(y + this.direction, x).getPiece() == null) {
            pawnMoves.add(new Move(start, board.get(y + this.direction, x), this));

            // Pawns can move two spaces if they are on their starting row
            if ((this.alliance == Alliance.WHITE && y == 6 || this.alliance == Alliance.BLACK && y == 1) 
                && board.get(y + 2 * this.direction, x).getPiece() == null) {
                pawnMoves.add(new Move(start, board.get(y + 2 * this.direction, x), this));
            }
        }

        // Pawns can only capture diagonally if there is a piece on the tile of a different alliance
        // Need to be careful of index out of bounds
        if (x + 1 < 8 && board.get(y + this.direction, x + 1).isOccupied() && board.get(y + this.direction, x + 1).getPiece().getAlliance() != this.alliance) {
            pawnMoves.add(new Move(start, board.get(y + this.direction, x + 1), this));
        }
        
        if (x - 1 > -1 && board.get(y + this.direction, x - 1).isOccupied() && board.get(y + this.direction, x - 1).getPiece().getAlliance() != this.alliance) {
            pawnMoves.add(new Move(start, board.get(y + this.direction, x - 1), this));
        }

        // En Passant NEEDS TO BE REVIEWED
        
        if (y == 3 && this.alliance == Alliance.WHITE) {
            if (x + 1 < 8 && board.get(y, x + 1).getPiece() instanceof Pawn && ((Pawn) board.get(y, x + 1).getPiece()).enPassant)
                pawnMoves.add(new Move(start, board.get(y - 1, x + 1), this, true));
            
            if (x - 1 > -1 && board.get(y, x - 1).getPiece() instanceof Pawn && ((Pawn) board.get(y, x - 1).getPiece()).enPassant)
                pawnMoves.add(new Move(start, board.get(y - 1, x - 1), this, true));
        } else if (y == 4 && this.alliance == Alliance.BLACK) {
            if (x + 1 < 8 && board.get(y, x + 1).getPiece() instanceof Pawn && ((Pawn) board.get(y, x + 1).getPiece()).enPassant)
                pawnMoves.add(new Move(start, board.get(y - 1, x + 1), this, true));
            
            if (x - 1 > -1 && board.get(y, x - 1).getPiece() instanceof Pawn && ((Pawn) board.get(y, x - 1).getPiece()).enPassant)
                pawnMoves.add(new Move(start, board.get(y - 1, x - 1), this, true));
        }
        return pawnMoves;
    }
}
