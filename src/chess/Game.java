package chess;

import java.util.*;

import chess.board.*;
import chess.player.*;
import chess.pieces.*;

// A container just for all of our components
public class Game {
    private final Player whitePlayer;
    private final Player blackPlayer;
    private List<Move> whiteMoves;
    private List<Move> blackMoves;
    private Move lastMove;
    private final Board board;
    private Alliance turn;
    private final BoardManager manager;

    public Game() {
        this.whiteMoves = new ArrayList<Move>();
        this.blackMoves = new ArrayList<Move>();
        this.board = new Board();
        this.lastMove = null;
        this.whitePlayer = new Player(Alliance.WHITE, this.board);
        this.blackPlayer = new Player(Alliance.BLACK, this.board);
        this.manager = new BoardManager(board, whiteMoves, blackMoves, blackPlayer, whitePlayer);
        this.turn = Alliance.WHITE;
        whitePlayer.getKing().addManager(manager);
        blackPlayer.getKing().addManager(manager);
    }

    public Board getBoard() {
        return this.board;
    }

    public BoardManager getManager() {
        return this.manager;
    }

    public Move getLastMove() {
        return this.lastMove;
    }

    public void setLastMove(Move move) {
        this.lastMove = move; // May also want to add something that will just change the content of the move
    }

    public Alliance getTurn() {
        return this.turn;
    }

    public void endTurn() {
        this.turn = this.turn == Alliance.WHITE ? Alliance.BLACK : Alliance.WHITE;
    }
}