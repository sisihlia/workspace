package com.example.sisily.puzzle_15;

/**
 * Created by sisily on 01/02/18.
 */

public class Position {

    /** 1-based column index of this place. */
    private final int x;

    /** 1-based row index of this place. */
    private final int y;

    /** Tile currently placed at this place. */
    private Tile tile;

    /** Board which this place belongs to. */
    private Board board;

    /** Create a new place with the given indices for the given board. */
    public Position (int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.board = board;
    }

    /** Create a new place with the given indices and a tile marked with
     * the given number for the given board. */
    public Position (int x, int y, int number, Board board) {
        this(x, y, board);
        tile = new Tile(number);
    }

    /** Return the 1-based column index of this place. */
    public int getX() {
        return x;
    }

    /** Return the 1-based row index of this place. */
    public int getY() {
        return y;
    }

    /** Does this place have a tile at it? */
    public boolean hasTile() {
        return tile != null;
    }

    /** Return the tile placed in this place; null is returned if no
     * tile is placed. */
    public Tile getTile() {
        return tile;
    }

    /** Place the given tile in this place. */
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    /** Is the tile in this place slidable? Return false if this place
     * is empty, i.e., no tile is placed. */
    public boolean slidable() {
        return hasTile() && board.slidable(this);
    }

    public void slide() {
        board.slide(getTile());
    }
}
