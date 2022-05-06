package chess.board;

import chess.pieces.*;

public class Board {
    private static String imgRoot = "../pieces/piece PNGs/";
    
    Tile[][] board;
    private static final int BOARD_SIZE = 8;

    public Board() {
        this.board = new Tile[BOARD_SIZE][BOARD_SIZE];
        initializeBoard();
        setBoard();
    }

    // Sets up the board for a new game
    private void setBoard() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            board[1][i].setPiece(new Pawn(Alliance.WHITE, imgRoot + "PWhite.png", board[1][i])); 
            board[6][i].setPiece(new Pawn(Alliance.BLACK, imgRoot + "PBlack.png", board[6][i]));

            // Need to set constructors properly
            switch (i) {
                case 0:
                    board[0][i].setPiece(new Rook(Alliance.WHITE, imgRoot +  "RWhite.png", board[0][i]));
                    board[0][BOARD_SIZE - 1 - i].setPiece(new Rook(Alliance.WHITE, imgRoot +  "RWhite.png", board[0][i]));
                    board[7][i].setPiece(new Rook(Alliance.BLACK, imgRoot +  "RBlack.png", board[7][i]));
                    board[0][BOARD_SIZE - 1 - i].setPiece(new Rook(Alliance.BLACK, imgRoot +  "RBlack.png", board[7][i]));
                    break;
                case 1:
                    board[0][i].setPiece(new Knight(Alliance.WHITE, imgRoot +  "NWhite.png", board[0][i]));
                    board[0][BOARD_SIZE - 1 - i].setPiece(new Knight(Alliance.WHITE, imgRoot +  "NWhite.png", board[0][i]));
                    board[7][i].setPiece(new Knight(Alliance.BLACK, imgRoot +  "NBlack.png", board[7][i]));
                    board[0][BOARD_SIZE - 1 - i].setPiece(new Knight(Alliance.BLACK, imgRoot +  "NBlack.png", board[7][i]));
                    break;
                case 2:
                    board[0][i].setPiece(new Bishop(Alliance.WHITE, imgRoot +  "BWhite.png", board[0][i]));
                    board[0][BOARD_SIZE - 1 - i].setPiece(new Bishop(Alliance.WHITE, imgRoot +  "BWhite.png", board[0][i]));
                    board[7][i].setPiece(new Bishop(Alliance.BLACK, imgRoot +  "BBlack.png", board[7][i]));
                    board[0][BOARD_SIZE - 1 - i].setPiece(new Bishop(Alliance.BLACK, imgRoot +  "BBlack.png", board[7][i]));
                    break;
                case 3:
                    board[0][i].setPiece(new Queen(Alliance.WHITE, imgRoot +  "QWhite.png", board[7][i]));
                    board[7][i].setPiece(new Queen(Alliance.BLACK, imgRoot +  "QBlack.png", board[7][i]));
                    break;
                case 4:
                    board[0][i].setPiece(new King(Alliance.WHITE, imgRoot +  "KWhite.png", board[7][i]));
                    board[7][i].setPiece(new King(Alliance.BLACK, imgRoot +  "KBlack.png", board[7][i]));
                    break;
            }
        }
    }

    private void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Tile(j, i);
            }
        }
    }

    public Tile get(int row, int col) {
        return board[row][col];
    }

    public void clearBoard() {
        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j].removePiece();
            }
        }
    }
}