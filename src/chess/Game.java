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
    private final Board board;
    private Alliance turn;
    private final BoardManager manager;

    public Game() {
        this.board = new Board();
        this.whitePlayer = new Player(Alliance.WHITE, this.board);
        this.blackPlayer = new Player(Alliance.BLACK, this.board);
        this.whiteMoves = whitePlayer.getMovesList();
        this.blackMoves = blackPlayer.getMovesList();
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

    public Alliance getTurn() {
        return this.turn;
    }

    public void endTurn() {
        this.turn = this.turn == Alliance.WHITE ? Alliance.BLACK : Alliance.WHITE;
    }
}