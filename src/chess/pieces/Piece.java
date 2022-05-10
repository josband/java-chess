package chess.pieces;

import java.util.*;
import javax.swing.ImageIcon;

import chess.board.*;

// Maybe add checking the diagonals and horizontal movements to this class for the 
// Queen, Rook, and Bishop to share through inheritance
public abstract class Piece {
    private ImageIcon img;
    protected Tile currLocation;
    protected final Alliance alliance;
    private boolean dead;
    protected int hashValue;

    public Piece(Alliance alliance, String imgPath, Tile location) {
        this.alliance = alliance;
        this.dead = false;
        this.currLocation = location;
        this.img = new ImageIcon(imgPath);
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

    public int getValue() {
        return hashValue;
    }

    public Tile getLocation() {
        return this.currLocation;
    }

    public void setLocation(Tile location) {
        if (location != null) {
            this.currLocation = location;
        }
    }

    public ImageIcon Image() {
        return this.img;
    }

    // USAGE FOR: Bishop and Queen
    protected void checkDiagonal(List<Move> moves, Board board, Tile start, int deltaX, int deltaY) {
        int currX = start.getX();
        int currY = start.getY();

        boolean done = false;
        while (!done) {
            currX += deltaX;
            currY += deltaY;
            try { 
                if (!board.get(currY, currX).isOccupied() || board.get(currY, currX).getPiece().getAlliance() != this.alliance) {
                    moves.add(new Move(start, board.get(currY, currX)));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }  
        }
    }

    // USAGE FOR: Rook and Queen
    protected void checkRankAndFile(List<Move> moves, Board board, Tile start) {
        int currX = start.getX();
        int currY = start.getY();

        boolean done = false;
        // Check tiles apporaching file H
        while (!done) {
            currX++;
            try { 
                if (!board.get(currY, currX).isOccupied() || board.get(currY, currX).getPiece().getAlliance() != this.alliance) {
                    moves.add(new Move(start, board.get(currY, currX)));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }  
        }

        done = false;
        currX = start.getX();
        // Check tiles apporaching file A
        while (!done) {
            currX--;
            try { 
                if (!board.get(currY, currX).isOccupied() || board.get(currY, currX).getPiece().getAlliance() != this.alliance) {
                    moves.add(new Move(start, board.get(currY, currX)));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }      
        }

        done = false;
        currX = start.getX();
        // Check tiles apporaching rank 8
        while (!done) {
            currY++;
            try { 
                if (!board.get(currY, currX).isOccupied() || board.get(currY, currX).getPiece().getAlliance() != this.alliance) {
                    moves.add(new Move(start, board.get(currY, currX)));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }    
        }

        done = false;
        currY = start.getY();
        // Check tiles apporaching rank 1
        while (!done) {
            currY--;
            try { 
                if (!board.get(currY, currX).isOccupied() || board.get(currY, currX).getPiece().getAlliance() != this.alliance) {
                    moves.add(new Move(start, board.get(currY, currX)));
                    done = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                done = true;
            }    
        }
    }
}
