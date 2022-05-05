package chess.pieces;

import java.util.ArrayList;

import chess.board.Board;
import chess.board.Move;
import chess.board.Tile;

public class Knight {
    // Knights move by going N, E, S, or W by 2 and then orthogonally 1
    // Therefore, one idea for the implementation of the night is to create
    // two static arrays of potion deltas going clockwise and iterating through
    // these deltas and checking if it is inbounds or not 
    
    // start with a move north and go clockwise
    private static int[] deltaY = {2, 1, -1, -2, -2, -1, 1, 2};
    private static int[] deltaX = {1, 2, 2, 1, -1, -2, -2, -1};

    public Knight(Alliance alliance, String imgPath) {
        super(alliance, imgPath); 
    }

    public List<Move> calculateLegalMoves(Board board, Tile start) {
        ArrayList<Move> knightMoves = new ArrayList<Move>();
        int x, y;
        x = start.getX();
        y = start.getY();

        for(int i = 0; i < deltaX.length; i++) {
            try {
                Tile moveLocation = board[y + deltaY[i]][x + deltaX[i]];
                if (!moveLocation.isOccupied() || moveLocation.getPiece().getAlliance() == this.alliance) {
                    knightMoves.add(new Move(start, moveLocation));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // Not valid! We can just continue
                continue;
            }
        }

        return knightMoves;
    }
}
