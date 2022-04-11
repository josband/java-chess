package chess.board;

import java.util.*;

public class Board {
    private Piece[][] board;
    LinkedList<Piece> blackActivePieces;
    LinkedList<Piece> whiteActivePieces;

    private final int BOARD_SIZE = 8;
    // private final static int[] VALUES = {1, 3, 3, 5, 9,
    //                                      -1, -3, -3, -5, -9};

    public Board() {
        board = new Piece[BOARD_SIZE][BOARD_SIZE];
        
        // Initialize the board with bloack and white pieces
        for(int i = 0; i < BOARD_SIZE; i++) {
            board[1][i] = new Pawn(); 
        }

        board[0][0] = new Rook();
        board[0][7] = new Rook();
        board[7][0] = new Rook();
        board[7][7] = new Rook();

        board[0][1] = new Knight();
        board[0][6] = new Knight();
        board[7][1] = new Knight();
        board[7][6] = new Knight();

        board[0][2] = new Bishop();
        board[0][5] = new Bishop();
        board[7][2] = new Bishop();
        board[7][5] = new Bishop();

        board[0][3] = new Queen();
        board[7][3] = new Queen();

        board[0][4] = new King();
        board[7][4] = new King();
    }
}