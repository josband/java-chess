package chess.board;

import chess.pieces.Piece;

public class Board {
    Tile[][] board;
    private static final int BOARD_SIZE = 8;

    public Board() {
        this.board = new Tile[BOARD_SIZE][BOARD_SIZE];
        setBoard();
    }

    // Sets up the board for a new game
    private void setBoard() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            board[1][i] = new Pawn(); 
            board[6][i] = new Pawn();

            // Need to set constructors properly
            switch (i) {
                case 0:
                    board[0][i] = new Rook();
                    board[0][BOARD_SIZE - 1 - i] = new Rook();
                    board[7][i] = new Rook();
                    board[0][BOARD_SIZE - 1 - i] = new Rook();
                    break;
                case 1:
                    board[0][i] = new Knight();
                    board[0][BOARD_SIZE - 1 - i] = new Knight();
                    board[7][i] = new Knight();
                    board[0][BOARD_SIZE - 1 - i] = new Knight();
                    break;
                case 2:
                    board[0][i] = new Bishop();
                    board[0][BOARD_SIZE - 1 - i] = new Bishop();
                    board[7][i] = new Bishop();
                    board[0][BOARD_SIZE - 1 - i] = new Bishop();
                    break;
                case 3:
                    board[0][i] = new Queen();
                    board[7][i] = new Queen();
                    break;
                case 4:
                    board[0][i] = new King();
                    board[7][i] = new King();
                    break;
            }
        }
    }

    public void clearBoard() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j].removePiece();
            }
        }
    }
}