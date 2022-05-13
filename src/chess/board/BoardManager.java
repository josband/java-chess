package chess.board;

import java.util.*;
import chess.pieces.*;
import chess.player.*;

public class BoardManager {
    private Board board;
    private King blackKing;
    private King whiteKing;
    private List<Move> whiteMoves;
    private List<Move> blackMoves;
    private HashMap<Tile, Move> attackMap;

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
        this.blackKing = black.getKing();
        this.whiteKing = white.getKing();
    }

    /**
     * Determines if a player's king is in check
     * @param player The player to be evaluated whether or not is in check
     */
    public boolean isChecked(Player player) {
        King king = player.getAlliance() == Alliance.WHITE ? whiteKing : blackKing;
        int x = king.getLocation().getX();
        int y = king.getLocation().getY();

        return isTileAttacked(king.getAlliance(), board.get(y, x));
    }

    /**
     * Determines if a player's king is mated
     * @param player The player to be evaluated whether or not is mated
     */
    public boolean isMated(Player player) {
        if (!isChecked(player)) 
            return false;
        else {
            King king = player.getAlliance() == Alliance.WHITE ? whiteKing : blackKing;
            int kingX = king.getLocation().getX();
            int kingY = king.getLocation().getY();

            for (int i = -1; i < 2; i++) {
                // Tile above
                try {
                    if (!isTileAttacked(king.getAlliance(), board.get(kingY + 1, kingX + i))) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                
                // Tile next to
                try {
                    if (i != 0 && !isTileAttacked(king.getAlliance(), board.get(kingY, kingX + i))) {
                        return false;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
    
                // Tile below
                try {
                    if (!isTileAttacked(king.getAlliance(), board.get(kingY - 1, kingX + i))) {
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
        updateAttackMap(alliance);
        return this.attackMap.get(tile) != null;
    }

    /**
     * Updates the attackMap HashMap so that the map contains moves that move TO that tile. The alliance passed into
     * updateAttackedMap determines the alliance that the map will be used for/knowing that the opponent is not 'alliance'. 
     * @param alliance the alliance that will be used to determine the OPPOSING moves that need to be hashed to
     */
    private void updateAttackMap(Alliance alliance) {
        List<Move> possibleMoves = alliance == Alliance.WHITE ? blackMoves : whiteMoves;
        attackMap.clear();

        for (Move move : possibleMoves) {
            attackMap.put(move.getTo(), move);
        }
    }
}
