package chess.board;

public class Move {
    private Tile from;
    private Tile to;

    public Move(Tile to, Tile from) {
        this.to = to;
        this.from = from;
    }

    public Tile getFrom() {
        return from;
    }

    public Tile getTo() {
        return to;
    }
}
