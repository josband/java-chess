package chess.pieces;

import chess.board.Board;
import chess.board.Tile;

// Maybe add checking the diagonals and horizontal movements to this class for the 
// Queen, Rook, and Bishop to share through inheritance
public abstract class Piece {
    private final Alliance alliance;
    private boolean dead;

    public Piece(Alliance alliance) {
        this.alliance = alliance;
        this.dead = false;
    }

    public abstract List<Move> calculateLegalMoves(Board board, Tile start);

    public boolean isDead() {
        return dead;
    }

    public void setDead() {
        this.dead = true;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    // USAGE FOR: Bishop and Queen
    private void checkDiagonal(List<Move> moves, Board board, Tile start, int deltaX, int deltaY) {
        int currX = start.getX();
        int currY = start.getY();

        boolean done = false;
        while (!done) {
            currX += deltaX;
            currY += deltaY;
            try { 
                if (!board[currY][currX].isOccupied() || board[currY][currX].getPiece().getAlliance() != this.alliance) {
                    moves.add(new Move(start, board[currY][currX]));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }  
        }
    }

    // USAGE FOR: Rook and Queen
    private void checkRankAndFile(List<Move> moves, Board board, Tile start) {
        int currX = start.getX();
        int currY = start.getY();

        boolean done = false;
        // Check tiles apporaching file H
        while (!done) {
            currX++;
            try { 
                if (!board[currY][currX].isOccupied() || board[currY][currX].getPiece().getAlliance() != this.alliance) {
                    moves.add(new Move(start, board[currY][currX]));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }  
        }

        done = false
        currX = start.getX();
        // Check tiles apporaching file A
        while (!done) {
            currX--;
            try { 
                if (!board[currY][currX].isOccupied() || board[currY][currX].getPiece().getAlliance() != this.alliance) {
                    moves.add(new Move(start, board[currY][currX]));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }  
        }

        done = false
        currX = start.getX();
        // Check tiles apporaching rank 8
        while (!done) {
            currY++;
            try { 
                if (!board[currY][currX].isOccupied() || board[currY][currX].getPiece().getAlliance() != this.alliance) {
                    moves.add(new Move(start, board[currY][currX]));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }  
        }

        done = false
        currY = start.getY();
        // Check tiles apporaching rank 1
        while (!done) {
            currY--;
            try { 
                if (!board[currY][currX].isOccupied() || board[currY][currX].getPiece().getAlliance() != this.alliance) {
                    moves.add(new Move(start, board[currY][currX]));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }  
        }
    }
}
