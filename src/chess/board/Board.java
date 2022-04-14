package chess.board;

import java.util.*;

import chess.pieces.Piece;

public class Board {
    private Tile[][] board;
    private List<Piece> blackActivePieces;
    private List<Piece> whiteActivePieces;
    private List<Move> moves;

    private final int BOARD_SIZE = 8;
    
    public Board() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
        
        // Initialize the board with bloack and white pieces
        for(int i = 0; i < BOARD_SIZE; i++) {
            // board[1][i] = new Pawn(); 
            // board[6][i] = new Pawn();

            // switch (i) {
            //     case 0:
            //         board[0][i] = new Rook();
            //         board[0][BOARD_SIZE - 1 - i] = new Rook();
            //         board[7][i] = new Rook();
            //         board[0][BOARD_SIZE - 1 - i] = new Rook();
            //         break;
            //     case 1:
            //         board[0][i] = new Knight();
            //         board[0][BOARD_SIZE - 1 - i] = new Knight();
            //         board[7][i] = new Knight();
            //         board[0][BOARD_SIZE - 1 - i] = new Knight();
            //         break;
            //     case 2:
            //         board[0][i] = new Bishop();
            //         board[0][BOARD_SIZE - 1 - i] = new Bishop();
            //         board[7][i] = new Bishop();
            //         board[0][BOARD_SIZE - 1 - i] = new Bishop();
            //         break;
            //     case 3:
            //         board[0][i] = new Queen();
            //         board[7][i] = new Queen();
            //         break;
            //     case 4:
            //         board[0][i] = new King();
            //         board[7][i] = new King();
            //         break;
            // }
        }
    }
}