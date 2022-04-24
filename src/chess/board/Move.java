package chess.board;

public class Move {
    private Tile from;
    private Tile to;

    public Move(Tile from, Tile to) {
        this.from = from;
        this.to = to;
    }

    public Tile getFrom() {
        return from;
    }

    public Tile getTo() {
        return to;
    }
}
