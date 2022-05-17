package chess.board;

import java.util.*;

import chess.pieces.*;
import chess.player.*;

public class BoardManager {
    private final Board board;
    private Move lastMove;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final List<Piece> whitePieces;
    private final List<Piece> blackPieces;

    /**
     * Creates an Object responsible for overviewing a board of pieces for the chess game. It is utilized
     * in checking whether tiles are attacked by enemy pieces, a player is in check, or a player has been mated.
     * @param board The board in which the game is occurring
     * @param whiteMoves The list of moves being used for white
     * @param blackMoves The list of moves being used for black
     * @param black The black player
     * @param white The white player
     */
    public BoardManager(Board board, Player black, Player white) {
        this.board = board;
        this.whitePieces = white.getPieces();
        this.blackPieces = black.getPieces();
        this.whitePlayer = white;
        this.blackPlayer = black;
    }

    /**
     * Determines if a player's king is in check
     * @param alliance The alliance to be evaluated whether or not is in check
     */
    public boolean isChecked(Alliance alliance) {
        King king = alliance == Alliance.WHITE ? whitePlayer.getKing() : blackPlayer.getKing();

        return isTileAttacked(king.getAlliance(), king.getLocation());
    }

    public boolean isStaleMated(Alliance alliance) {
        if (isChecked(alliance))
            return false;
        return getAllLegalMoves(alliance).isEmpty();
    }

    /**
     * Determines if a player's king is mated
     * @param alliance The alliance to be evaluated whether or not is mated
     */
    public boolean isMated(Alliance alliance) {
        if (!isChecked(alliance)) {
            return false;
        }
        List<Piece> pieces = alliance == Alliance.WHITE ? whitePieces : blackPieces;
        for (Piece piece : pieces) {
            for (Move move : piece.calculateLegalMoves(board, piece.getLocation())) {
                if (testMove(move)) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<Move> getAllLegalMoves(Alliance alliance) {
        List<Piece> pieces = alliance == Alliance.WHITE ? whitePieces : blackPieces;
        List<Move> moves = new ArrayList<Move>();
        for (Piece piece : pieces) {
            for (Move move : piece.calculateLegalMoves(board, piece.getLocation())) {
                if (testMove(move)) {
                    moves.add(move);
                }
            }
        }
        return moves;
    }

    /**
     * Determines if there is an enemy move that attacks a tile. Note that based on the Java API that if there
     * are many moves that attack the same tile (which happens quite often in chess), all but one of the moves 
     * will be evicted. This is okay we are just concerned whether or not that tile is attacked
     * @param alliance The alliance to check that is being attacked
     * @param tile The tiles to determine that is being attacked
     * @return Whether or not a piece of 'alliance' could be attacked at 'tile' 
     */
    public boolean isTileAttacked(Alliance alliance, Tile tile) {
        return checkDiagonalAttacks(alliance, tile) || checkRankAndFileAttacks(alliance, tile) || checkKnightAttacks(alliance, tile);
    }

    private boolean checkDiagonalAttacks(Alliance alliance, Tile tile) {
        int currX = tile.getX() + 1;
        int currY = tile.getY() + 1;

        while (currX < 8 && currY < 8) {
            Tile checkTile = board.get(currY, currX);
            Piece piece = checkTile.getPiece();
            if (checkTile.isOccupied() && alliance != piece.getAlliance()) {
                if (piece instanceof Queen || piece instanceof Bishop || (Math.abs(currX - tile.getX()) == 1 && piece instanceof King)) {
                    return true;
                } else if (Math.abs(tile.getX() - currX) == 1 && piece instanceof Pawn && alliance == Alliance.BLACK) {
                    return true;
                } else {
                    break;
                }
            } else if (checkTile.isOccupied() && alliance == piece.getAlliance()) {
                break;
            }
            currX += 1;
            currY += 1;
        }

        currX = tile.getX() - 1;
        currY = tile.getY() - 1;
        while (currX > -1 && currY > -1) {
            Tile checkTile = board.get(currY, currX);
            Piece piece = checkTile.getPiece();
            if (checkTile.isOccupied() && alliance != piece.getAlliance()) {
                if (piece instanceof Queen || piece instanceof Bishop || (Math.abs(currX - tile.getX()) == 1 && piece instanceof King)) {
                    return true;
                } else if (Math.abs(tile.getX() - currX) == 1 && piece instanceof Pawn && alliance == Alliance.WHITE) {
                    return true;
                } else {
                    break;
                }
            } else if (checkTile.isOccupied() && alliance == piece.getAlliance()) {
                break;
            }
            currX += -1;
            currY += -1;
        }

        currX = tile.getX() - 1;
        currY = tile.getY() + 1;
        while (currX > -1 && currY < 8) {
            Tile checkTile = board.get(currY, currX);
            Piece piece = checkTile.getPiece();
            if (checkTile.isOccupied() && alliance != piece.getAlliance()) {
                if (piece instanceof Queen || piece instanceof Bishop || (Math.abs(currX - tile.getX()) == 1 && piece instanceof King)) {
                    return true;
                } else if (Math.abs(tile.getX() - currX) == 1 && piece instanceof Pawn && alliance == Alliance.BLACK) {
                    return true;
                } else {
                    break;
                }
            } else if (checkTile.isOccupied() && alliance == piece.getAlliance()) {
                break;
            }
            currX += -1;
            currY += 1;
        }

        currX = tile.getX() + 1;
        currY = tile.getY() - 1;
        while (currX < 8 && currY > -1) {
            Tile checkTile = board.get(currY, currX);
            Piece piece = checkTile.getPiece();
            if (checkTile.isOccupied() && alliance != piece.getAlliance()) {
                if (piece instanceof Queen || piece instanceof Bishop || (Math.abs(currX - tile.getX()) == 1 && piece instanceof King)) {
                    return true;
                } else if (Math.abs(tile.getX() - currX) == 1 && piece instanceof Pawn && alliance == Alliance.WHITE) {
                    return true;
                } else {
                    break;
                }
            } else if (checkTile.isOccupied() && alliance == piece.getAlliance()) {
                break;
            }
            currX += 1;
            currY += -1;
        }

        return false;
    }

    private boolean checkRankAndFileAttacks(Alliance alliance, Tile tile) {
        // Check tiles apporaching file H
        int currX = tile.getX() + 1;
        int currY = tile.getY();
        while (currX < 8) {
            Tile checkTile = board.get(currY, currX);
            Piece piece = checkTile.getPiece();
            if (checkTile.isOccupied() && alliance != piece.getAlliance()) {
                if (piece instanceof Rook || piece instanceof Queen || (piece instanceof King && Math.abs(currX - tile.getX()) == 1)) {
                    return true;
                } else {
                    break;
                }
            } else if (checkTile.isOccupied() && alliance == piece.getAlliance()) {
                break;
            }
            currX++;
        }

        // Check tiles apporaching file A
        currX = tile.getX() - 1;
        while (currX > -1) {
            Tile checkTile = board.get(currY, currX);
            Piece piece = checkTile.getPiece();
            if (checkTile.isOccupied() && alliance != piece.getAlliance()) {
                if (piece instanceof Rook || piece instanceof Queen || (piece instanceof King && Math.abs(currX - tile.getX()) == 1)) {
                    return true;
                } else {
                    break;
                }
            } else if (checkTile.isOccupied() && piece.getAlliance() == alliance) {
                break;
            }
            currX--;     
        }

        // Check tiles apporaching rank 8
        currX = tile.getX();
        currY = tile.getY() + 1;
        while (currY < 8) {
            Tile checkTile = board.get(currY, currX);
            Piece piece = checkTile.getPiece();
            if (checkTile.isOccupied() && piece.getAlliance() != alliance) {
                if (piece instanceof Rook || piece instanceof Queen || (piece instanceof King && Math.abs(currY - tile.getY()) == 1)) {
                    return true;
                } else {
                    break;
                }
            }
             else if (checkTile.isOccupied() && piece.getAlliance() == alliance) {
                break;
            }
            currY++;     
        }

        // Check tiles apporaching rank 1
        currY = tile.getY() - 1;
        while (currY > -1) {
            Tile checkTile = board.get(currY, currX);
            Piece piece = checkTile.getPiece();
            if (checkTile.isOccupied() && piece.getAlliance() != alliance) {
                if (piece instanceof Rook || piece instanceof Queen || (piece instanceof King && Math.abs(currY - tile.getY()) == 1)) {
                    return true;
                } else {
                    break;
                }
            } else if (checkTile.isOccupied() && piece.getAlliance() != alliance) {
                break;
            }
            currY--;     
        }

        return false;
    }

    private boolean checkKnightAttacks(Alliance alliance, Tile tile) {
        int[] deltaY = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] deltaX = {1, 2, 2, 1, -1, -2, -2, -1};
        int x = tile.getX();
        int y = tile.getY();

        for(int i = 0; i < deltaX.length; i++) {
            try {
                Tile checkTile = board.get(y + deltaY[i], x + deltaX[i]);
                Piece piece = checkTile.getPiece();
                if (checkTile.isOccupied() && piece.getAlliance() != alliance && 
                piece instanceof Knight) {
                
                return true;
            }
            } catch (ArrayIndexOutOfBoundsException e) {
                // Not valid! We can just continue
                continue;
            }
        }

        return false;
    }

    public void executeMove(Move move, boolean test) {
        if (move == null) {
            return;
        }
        Tile source = move.getFrom();
        Tile destination = move.getTo();
        Piece movedPiece = move.getMovedPiece();

        // Execute Initial phase of Move
        Piece removedPiece = null;
        List<Piece> pieces = movedPiece.getAlliance() == Alliance.BLACK ? whitePlayer.getPieces() : blackPlayer.getPieces();
        if (destination.isOccupied()) {
            removedPiece = destination.getPiece();
            pieces.remove(removedPiece);
        }
        source.setPiece(null);
        destination.setPiece(movedPiece);
        movedPiece.setLocation(destination);

        // Consider castling & En Passant
        if (move.isCastling()) {
            executeCastle(move);
        } else if (move.isEnPassant()) {
            executeEnPassant(move, pieces);
        }

        if (!test) {
            lastMove = move;
            // Check if we need to change enPassant of a Pawn
            if (lastMove != null && lastMove.getMovedPiece() instanceof Pawn && Math.abs(lastMove.getFrom().getY() - lastMove.getTo().getY()) == 2) {
                ((Pawn) lastMove.getMovedPiece()).setEnPassant(false);
            }

            // Check if we have moved a rook, king, or pawn that can be enPassant for the next move
            if (movedPiece instanceof King) {
                ((King) movedPiece).setMoved();
            } else if (movedPiece instanceof Rook) {
                ((Rook) movedPiece).setMoved();
            } else if (movedPiece instanceof Pawn && Math.abs(source.getY() - destination.getY()) == 2) {
                ((Pawn) movedPiece).setEnPassant(true);
            }
        }
    }

    private void executeEnPassant(Move move, List<Piece> pieces) {
        Tile destination = move.getTo();
        Piece movedPiece = move.getMovedPiece();
        int deltaY = movedPiece.getAlliance() == Alliance.WHITE ? 1 : -1;
        Piece removedPiece = board.get(destination.getY() + deltaY, destination.getX()).getPiece();
        board.get(destination.getY() + deltaY, destination.getX()).removePiece();
        pieces.remove(removedPiece);
    }

    private void executeCastle(Move move) {
        Player player = move.getMovedPiece().getAlliance() == Alliance.WHITE ? whitePlayer : blackPlayer;
        Rook rook;
        Tile source, destination;
        int y = player.getAlliance() == Alliance.WHITE ? 7 : 0;
        int x;

        if (move.isQueenCastling()) {
            rook = player.getQueenRook();
            x = 3;
            destination = board.get(y, x);
        } else {
            rook = player.getKingRook();
            x = 5;
            destination = board.get(y, x);
        }
        
        source = rook.getLocation();
        source.setPiece(null);
        destination.setPiece(rook);
        rook.setLocation(destination);
    }

    public boolean testMove(Move move) {
        if (move == null) {
            return false;
        }

        boolean result;
        Piece removedPiece;
        if (move.isEnPassant()) {
            Tile destination = move.getTo();
            int deltaY = move.getMovedPiece().getAlliance() == Alliance.WHITE ? 1 : -1;
            removedPiece = board.get(destination.getY() + deltaY, destination.getX()).getPiece();
        } else {
            removedPiece = move.getTo().getPiece();
        }

        executeMove(move, true);
        if (isChecked(move.getMovedPiece().getAlliance())) {
            result = false;
        } else {
            result = true;
        }
        undoMove(move, removedPiece);
        return result;
    }

    private void undoMove(Move move, Piece removedPiece) {
        if (move == null) {
            return;
        }
        Tile source = move.getFrom();
        Tile destination = move.getTo();
        Piece movedPiece = move.getMovedPiece();
        List<Piece> pieces = movedPiece.getAlliance() == Alliance.BLACK ? whitePlayer.getPieces() : blackPlayer.getPieces();

        // Undo castling and En Passant
        if (move.isCastling()) {
            undoCastle(move);
        } else if (move.isEnPassant()) {
            undoEnPassant(move, removedPiece, pieces);
        }
        
        // Undo the initial phase of the move
        source.setPiece(movedPiece);
        destination.setPiece(null);
        movedPiece.setLocation(source);
        if (removedPiece != null && !move.isEnPassant()) {
            destination.setPiece(removedPiece);
            removedPiece.setLocation(destination);
            pieces.add(removedPiece);
        }
    }

    private void undoCastle(Move move) {
        Player player = move.getMovedPiece().getAlliance() == Alliance.WHITE ? whitePlayer : blackPlayer;
        Rook rook;
        Tile source, destination;
        int y = player.getAlliance() == Alliance.WHITE ? 7 : 0;
        int x;

        if (move.isQueenCastling()) {
            rook = player.getQueenRook();
            x = 0;
            destination = board.get(y, x);
        } else {
            rook = player.getKingRook();
            x = 7;
            destination = board.get(y, x);
        }
        
        source = rook.getLocation();
        source.setPiece(null);
        destination.setPiece(rook);
        rook.setLocation(destination);
    }

    private void undoEnPassant(Move move, Piece removedPiece, List<Piece> pieces) {
        Tile removedPieceTile = removedPiece.getLocation();
        removedPieceTile.setPiece(removedPiece);
        pieces.add(removedPiece);
    }

    public void addCastlingMoves(Alliance alliance, List<Move> kingMoves) { // Change over so that it is part of king's call
        Player player = alliance == Alliance.WHITE ? whitePlayer : blackPlayer;
        King king = player.getKing();
        Rook queenRook = player.getQueenRook();
        Rook kingRook = player.getKingRook();
        int x = king.getLocation().getX();
        int y = king.getLocation().getY();

        if (isChecked(king.getAlliance()) || !king.firstMove())
            return;

        if (kingRook.firstMove() && !board.get(y, x + 1).isOccupied() && !board.get(y, x + 2).isOccupied()
            && !isTileAttacked(alliance, board.get(y, x + 1)) && !isTileAttacked(alliance, board.get(y, x + 2))) {

            kingMoves.add(new Move(king.getLocation(), board.get(y, x + 2), king, true, false));
        }
        
        if (queenRook.firstMove() && !board.get(y, x - 3).isOccupied() && !board.get(y, x - 2).isOccupied() && !board.get(y, x - 1).isOccupied()
            && !isTileAttacked(alliance, board.get(y, x - 1)) && !isTileAttacked(alliance, board.get(y, x - 2))) {

            kingMoves.add(new Move(king.getLocation(), board.get(y, x - 2), king, false, true));
        }
    }
}
