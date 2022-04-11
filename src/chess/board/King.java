package chess.board;

public class King extends Piece {
    private boolean castlingmove;

    public King(boolean isWhite, Tile location) {
        super(isWhite, location);
        castlingmove = false;
    }

    public boolean canCastle() {
        return castlingmove;
    }

    public boolean isValidMove(Tile from, Tile to) {
        if (from == null || to == null) {
            return false;
        }

        int deltaX, deltaY;

        deltaX = from.getX() - to.getX();
        deltaY = from.getY() - to.getY();

        // Can replace with a standard return but leaving it here for now
        if (Math.abs(deltaX) <= 1 && Math.abs(deltaY) <= 1 && !inCheck()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean inCheck() {
        return false;
    }
}
