package chess.board;

import java.util.*;

import chess.pieces.*;
import chess.player.*;

public class BoardManager {
    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final List<Move> whiteMoves;
    private final List<Move> blackMoves;
    private final HashMap<Tile, Move> attackMap;

    // TODO: check if I need to get rid of the attackMap

    /**
     * Creates an Object responsible for overviewing a board of pieces for the chess game. It is utilized
     * in checking whether tiles are attacked by enemy pieces, a player is in check, or a player has been mated.
     * @param board The board in which the game is occurring
     * @param whiteMoves The list of moves being used for white
     * @param blackMoves The list of moves being used for black
     * @param black The black player
     * @param white The white player
     */
    public BoardManager(Board board, List<Move> whiteMoves, List<Move> blackMoves, Player black, Player white) {
        this.board = board;
        this.attackMap = new HashMap<Tile, Move>();
        this.whiteMoves = whiteMoves;
        this.blackMoves = blackMoves;
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

    /**
     * Determines if a player's king is mated
     * @param alliance The alliance to be evaluated whether or not is mated
     */
    public boolean isMated(Alliance alliance) {
        if (!isChecked(alliance)) 
            return false;
        else {
            King king = alliance == Alliance.WHITE ? whitePlayer.getKing() : blackPlayer.getKing();
            int kingX = king.getLocation().getX();
            int kingY = king.getLocation().getY();

            for (int i = -1; i < 2; i++) {
                // Tile above
                try {
                    if (board.get(kingY + 1, kingX + i).isOccupied() || !isTileAttacked(king.getAlliance(), board.get(kingY + 1, kingX + i))) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                
                // Tile next to
                try {
                    if (i != 0 &&(board.get(kingY, kingX + i).isOccupied() || !isTileAttacked(king.getAlliance(), board.get(kingY, kingX + i)))) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
    
                // Tile below
                try {
                    if (board.get(kingY - 1, kingX + i).isOccupied() || !isTileAttacked(king.getAlliance(), board.get(kingY - 1, kingX + i))) {
                       return false; 
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
            
            return true;
        }
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
            if (checkTile.isOccupied() && piece.getAlliance() != alliance && 
                (piece instanceof Rook || piece instanceof Queen || (piece instanceof King && Math.abs(currX - tile.getX()) == 1))) {
                
                return true;
            } else if (checkTile.isOccupied() && piece.getAlliance() == alliance) {
                break;
            }
            currX++;
        }

        // Check tiles apporaching file A
        currX = tile.getX() - 1;
        while (currX > -1) {
            Tile checkTile = board.get(currY, currX);
            Piece piece = checkTile.getPiece();
            if (checkTile.isOccupied() && piece.getAlliance() != alliance && 
                (piece instanceof Rook || piece instanceof Queen || (piece instanceof King && Math.abs(currX - tile.getX()) == 1))) {
                
                return true;
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
            if (checkTile.isOccupied() && piece.getAlliance() != alliance && 
                (piece instanceof Rook || piece instanceof Queen || (piece instanceof King && Math.abs(currY - tile.getY()) == 1))) {
                
                return true;
            } else if (checkTile.isOccupied() && piece.getAlliance() == alliance) {
                break;
            }
            currY++;     
        }

        // Check tiles apporaching rank 1
        currY = tile.getY() - 1;
        while (currY > -1) {
            Tile checkTile = board.get(currY, currX);
            Piece piece = checkTile.getPiece();
            if (checkTile.isOccupied() && piece.getAlliance() != alliance && 
                ((piece instanceof Rook || piece instanceof Queen || (piece instanceof King && Math.abs(currY - tile.getY()) == 1)))) {
                
                return true;
            } else if (checkTile.isOccupied() && piece.getAlliance() == alliance) {
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

    /**
     * Updates the attackMap HashMap so that the map contains moves that move TO that tile. The alliance passed into
     * updateAttackedMap determines the alliance that the map will be used for/knowing that the opponent is not 'alliance'. 
     * @param alliance the alliance that will be used to determine the OPPOSING moves that need to be hashed to
     */
    public void updateAttackMap(Alliance alliance) { 
        List<Move> possibleMoves = alliance == Alliance.WHITE ? blackMoves : whiteMoves;
        updatePossibleMoves(possibleMoves, alliance);
        attackMap.clear();

        for (Move move : possibleMoves) {
            attackMap.put(move.getTo(), move);
        }
    }

    private void updatePossibleMoves(List<Move> possibleMoves, Alliance alliance) {
        List<Piece> pieces = alliance == Alliance.WHITE ? blackPlayer.getPieces() : whitePlayer.getPieces();

        possibleMoves.clear();
        for (Piece piece : pieces) {
            possibleMoves.addAll(piece.calculateLegalMoves(board, piece.getLocation()));
        }
    }

    public void executeMove(Move move) {
        if (move == null) {
            return;
        }
        Tile source = move.getFrom();
        Tile destination = move.getTo();
        Piece movedPiece = move.getMovedPiece();

        // TODO: Add EnPassant

        // Execute Initial phase of Move
        Piece removedPiece = null;
        if (source.isOccupied()) {
            removedPiece = source.getPiece();
            List<Piece> pieces = removedPiece.getAlliance() == Alliance.WHITE ? whitePlayer.getPieces() : blackPlayer.getPieces();
            pieces.remove(removedPiece);
        }
        source.setPiece(null);
        destination.setPiece(movedPiece);
        movedPiece.setLocation(destination);

        // Check if we have moved a rook or kingk
        if (movedPiece instanceof King) {
            ((King) movedPiece).setMoved();
        } else if (movedPiece instanceof Rook) {
            ((Rook) movedPiece).setMoved();
        }

        // Consider castling
        if (move.isCastling()) {
            executeCastle(move);
        }
    }

    private void executeCastle(Move move) {
        Player player = move.getMovedPiece().getAlliance() == Alliance.WHITE ? whitePlayer : blackPlayer;
        Rook rook;
        Tile source, destination;
        int y = player.getAlliance() == Alliance.WHITE ? 7 : 0;
        int x;

        if (move.isQueenCastling()) {
            rook = player.getQueenRook();
            x = rook.getLocation().getX() == 0 ? 3 : 5;
            destination = board.get(y, x);
        } else {
            rook = player.getKingRook();
            x = rook.getLocation().getX() == 0 ? 3 : 5;
            destination = board.get(y, x);
        }
        
        source = rook.getLocation();
        source.setPiece(null);
        destination.setPiece(rook);
        rook.setLocation(destination);
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
