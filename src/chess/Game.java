package chess;

import java.util.*;

import chess.board.*;
import chess.player.*;
import chess.pieces.*;

// THIS WILL BE IMPLEMENTED BEFORE LAST
public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private List<Move> whiteMoves;
    private List<Move> blackMoves;
    private Board board;
    private boolean whiteTurn;

    public Game() {
        whitePlayer = new Player(Alliance.WHITE);
        blackPlayer = new Player(Alliance.BLACK);
        whiteMoves = new ArrayList<Move>();
        blackMoves = new ArrayList<Move>();
        Board board = new Board();
        whiteTurn = true;
    }

    public void run() {
        List<Piece> playerPieces;
        List<Move> playerMoves;
        while (true) {
            playerPieces = whiteTurn ? this.whitePlayer.getPieces() : this.blackPlayer.getPieces();
            playerMoves = whiteTurn ? this.whiteMoves : this.blackMoves;

            for (Piece piece : playerPieces) {
                playerMoves.add(piece.calculateLegalMoves(this.board, start)); // ERROR NEED TO ADD PART THAT HANDLES START ARGUMENMT
            }




            playerMoves.clear();
            whiteTurn = !whiteTurn;
        }
    }
}