package chess.board;

import chess.pieces.*;

public class Board {
    private static String imgRoot = "./src/chess/pieces/piece PNGs/";
    
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
            board[6][i].setPiece(new Pawn(Alliance.WHITE, imgRoot + "wP.png", board[1][i])); 
            board[1][i].setPiece(new Pawn(Alliance.BLACK, imgRoot + "bP.png", board[6][i]));

            // Need to set constructors properly
            switch (i) {
                case 0:
                    board[7][i].setPiece(new Rook(Alliance.WHITE, imgRoot +  "wR.png", board[7][i]));
                    board[7][BOARD_SIZE - 1 - i].setPiece(new Rook(Alliance.WHITE, imgRoot +  "wR.png", board[7][i]));
                    board[0][i].setPiece(new Rook(Alliance.BLACK, imgRoot +  "bR.png", board[0][i]));
                    board[0][BOARD_SIZE - 1 - i].setPiece(new Rook(Alliance.BLACK, imgRoot +  "bR.png", board[0][i]));
                    break;
                case 1:
                    board[7][i].setPiece(new Knight(Alliance.WHITE, imgRoot +  "wN.png", board[7][i]));
                    board[7][BOARD_SIZE - 1 - i].setPiece(new Knight(Alliance.WHITE, imgRoot +  "wN.png", board[7][i]));
                    board[0][i].setPiece(new Knight(Alliance.BLACK, imgRoot +  "bN.png", board[0][i]));
                    board[0][BOARD_SIZE - 1 - i].setPiece(new Knight(Alliance.BLACK, imgRoot +  "bN.png", board[0][i]));
                    break;
                case 2:
                    board[7][i].setPiece(new Bishop(Alliance.WHITE, imgRoot +  "wB.png", board[7][i]));
                    board[7][BOARD_SIZE - 1 - i].setPiece(new Bishop(Alliance.WHITE, imgRoot +  "wB.png", board[7][i]));
                    board[0][i].setPiece(new Bishop(Alliance.BLACK, imgRoot +  "bB.png", board[0][i]));
                    board[0][BOARD_SIZE - 1 - i].setPiece(new Bishop(Alliance.BLACK, imgRoot +  "bB.png", board[0][i]));
                    break;
                case 3:
                    board[7][i].setPiece(new Queen(Alliance.WHITE, imgRoot +  "wQ.png", board[7][i]));
                    board[0][i].setPiece(new Queen(Alliance.BLACK, imgRoot +  "bQ.png", board[0][i]));
                    break;
                case 4:
                    board[7][i].setPiece(new King(Alliance.WHITE, imgRoot +  "wK.png", board[7][i]));
                    board[0][i].setPiece(new King(Alliance.BLACK, imgRoot +  "bK.png", board[0][i]));
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

    public void executeMove(Move move) {
        Tile source = move.getFrom();
        Tile destination = move.getTo();
        Piece movedPiece = move.getMovedPiece();

        source.setPiece(null);
        destination.setPiece(movedPiece);
    }
}