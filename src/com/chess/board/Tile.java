package com.chess.board;
import com.chess.pieces.Piece;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
// Making the class immutable

public abstract class Tile {
    private final int tileCoordinate;

    private Tile (int tileCoordinate)//Private constructor
    {
        this.tileCoordinate = tileCoordinate;
    }

    private static Map<Integer,EmptyTile> createAllPossibleTiles()
    {
        Map<Integer,EmptyTile> EMPTY_TILES = new HashMap<>();
        for(int i=0; i<64; i++)
        {
            EMPTY_TILES.put(i,new EmptyTile(i));
        }
        return ImmutableMap.copyOf(EMPTY_TILES);//ImmutableMap cannot be changed later
    }

    public static Tile createTile(int tileCoordinate,Piece piece)
    {
        if(piece != null)
        {
            return new OccupiedTile(tileCoordinate,piece);
        }

        else
        {
            return EMPTY_TILES.get(tileCoordinate);//return empty tile from the map EMPTY_TILES
        }
    }


    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleTiles();


    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {
        private EmptyTile (int coordinate)
        {
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {
        private final Piece pieceOnTile; //The piece on tile can change even though its final
        private OccupiedTile (int coordinate, Piece pieceOnTile)
        {
            super(coordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {

            return this.pieceOnTile;
        }
    }

}
