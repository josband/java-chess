package chess;

import java.util.*;

import chess.board.*;
import chess.player.*;
import chess.pieces.*;

// THIS WILL BE IMPLEMENTED FULLY NEAR THE END OF DEVELOPMENT
public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private List<Move> whiteMoves;
    private List<Move> blackMoves;
    private Board board;
    private boolean whiteTurn;

    public Game() {
        this.whitePlayer = new Player(Alliance.WHITE);
        this.blackPlayer = new Player(Alliance.BLACK);
        this.whiteMoves = new ArrayList<Move>();
        this.blackMoves = new ArrayList<Move>();
        this.board = new Board();
        this.whiteTurn = true;
    }

    public void run() {
        List<Piece> playerPieces;
        List<Move> playerMoves;

        // Will replace condition with our checkmate checker method
        while (true) {
            playerPieces = whiteTurn ? this.whitePlayer.getPieces() : this.blackPlayer.getPieces();
            playerMoves = whiteTurn ? this.whiteMoves : this.blackMoves;

            // For every one of the players pieces, I will calculate 'legal' moves even though some of them may not actually be legal
            // However I will be making a Check/Checkmate class that checks for this and will clean the list of moves
            for (Piece piece : playerPieces) {
                playerMoves.addAll(piece.calculateLegalMoves(this.board, piece.getLocation())); // ERROR NEED TO ADD PART THAT HANDLES START ARGUMENMT
            }

            // DO THE MOVE: TO BE IMPLEMENTED
            // DO THE MOVE: TO BE IMPLEMENTED
            // DO THE MOVE: TO BE IMPLEMENTED

            playerMoves.clear();
            whiteTurn = !whiteTurn;
        }
    }

    public Board getBoard() {
        return this.board;
    }
}