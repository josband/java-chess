package chess;

import java.util.ArrayList;

import chess.board.Board;
import chess.pieces.Alliance;
import chess.player.Player;

// THIS WILL BE IMPLEMENTED BEFORE LAST
public class Game {
    private Player whitePlayer;
    private Player blackPlayer;
    private List<Move> whiteMoves;
    private List<Move> blackMoves;
    private Board board;

    public Game() {
        whitePlayer = new Player(Alliance.WHITE);
        blackPlayer = new Player(Alliance.BLACK);
        whiteMoves = new ArrayList<Move>();
        blackMoves = new ArrayList<Move>();
        Board board = new Board();
    }
}